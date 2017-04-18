package com.crazyhoorse961.sourcedrops.utils;/**
 * Created by nini7 on 01.04.2017.
 */


import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * @author crazyhoorse961
 */
public class Dumper {

    public String generateHaste() throws IOException {
        String key = Pastebin.KEY.get();
        PastebinFactory fac = new PastebinFactory();
        PasteBuilder builder = fac.createPaste();
        com.besaba.revonline.pastebinapi.Pastebin pbin = fac.createPastebin(key);
        builder.setTitle("Dump #" + getDumpTime());
        builder.setRaw(getErrors() + " \n" + getChecksum());
        builder.setMachineFriendlyLanguage("text");
        builder.setVisiblity(PasteVisiblity.Unlisted);
        builder.setExpire(PasteExpire.Never);
        Paste past = builder.build();
        Response<String> risp = pbin.post(past);
        if (risp.hasError()) {
            Bukkit.getLogger().log(Level.SEVERE, "Critical error occured while pasting dump!!!!!");
            return ChatColor.RED + "Severe error occured. Do you have internet?";
        }
        return risp.get();
    }

    private String getChecksum() {
        try (InputStream is = new FileInputStream(Dumper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())) {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] usg = new byte[4096];
            int lenght;
            while ((lenght = is.read(usg)) > 0) {
                md.update(usg, 0, lenght);
            }
            return DatatypeConverter.printHexBinary(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private long getDumpTime() {
        long start = System.nanoTime();
        long count = 0L;
        for (long x = 0; x < Integer.MAX_VALUE; x++) {
            count += 1;
        }
        long end = System.nanoTime();
        return end - start;
    }

    private String getErrors() {
        List<String> data = new ArrayList<String>();
        for (Map.Entry<Thread, StackTraceElement[]> thr : Thread.getAllStackTraces().entrySet()) {
            StringJoiner joiner = new StringJoiner("\n", thr.getKey().getName() + "\n{\n", "\n}");
            boolean containsError = false;
            for (StackTraceElement elem : thr.getValue()) {
                if (elem.getClassName().contains("com.crazyhoorse961.sourcedrops")) {
                    joiner.add(" Error at line " + elem.getLineNumber() + " at method " + elem.getMethodName() + " at class " + elem.getClassName() + " (" + elem.getFileName() + ")");
                    containsError = true;
                }
            }
            if (containsError)
                data.add(joiner.toString());
        }
        return data.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

}
