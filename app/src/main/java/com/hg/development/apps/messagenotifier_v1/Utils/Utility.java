/**
 * Copyright 2014
 *
 *
 * Sous licence Apache, Version 2.0 (la "Licence");
 * Vous ne pouvez pas utiliser ce fichier sauf en conformité avec la licence.
 * Vous pouvez obtenir une copie de la licence à l'adresse :
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Sauf si requis par la loi en vigueur ou accord écrit, le logiciel
 * Distribué sous licence est distribué «TEL QUEL»,
 * SANS GARANTIES OU CONDITIONS D'AUCUNE SORTE, express ou implicite.
 * Voir la licence pour les autorisations spécifiques aux différentes langues et
 * Limitations sous la licence.
 *
 * Contribué par : Hivinau GRAFFE
 */
package com.hg.development.apps.messagenotifier_v1.Utils;

import android.graphics.Bitmap;
import android.util.Base64;

import com.hg.development.apps.messagenotifier_v1.Contact.Person.*;
import com.hg.development.apps.messagenotifier_v1.Contact.Person.Number;

import org.apache.http.conn.util.InetAddressUtils;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class : Utility
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class Utility {
    private static Pattern pattern_IP;
    private static Matcher matcher;

    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final String PORT_PATTERN = "^[0-9]+$";

    /**
     * Valider l'adresse IP avec l'expression régulière.
     * @param ip adresse ip de validation
     * @return <i>true</i> adresse IP valide, adresse IP non valide <i>false</i>
     * @throws Exception
     */
    public static boolean validate_ip(final String ip) throws Exception
    {
        try
        {
            pattern_IP = Pattern.compile(IPADDRESS_PATTERN);
            matcher = pattern_IP.matcher(ip);

            boolean isMatch = matcher.matches();

            if(!isMatch)
                throw new Exception("Adresse IP non valide !");

            return isMatch;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Valider le numéro de port avec l'expression régulière.
     * @param port
     * @return
     * @throws Exception
     */
    public static boolean validate_port(final String port) throws Exception
    {
        try
        {
            pattern_IP = Pattern.compile(PORT_PATTERN);
            matcher = pattern_IP.matcher(port);

            boolean isMatch = matcher.matches();

            if(isMatch)
            {
                int n_port = Integer.valueOf(port);
                if(n_port>=0 && n_port<=65535)
                    return true;
            }

            throw new Exception();
        }
        catch(Exception ex)
        {
            throw new Exception("Port non valide !");
        }
    }


    /**
     * Permet de supprimer les doublons dans la liste des numéros d'un contact.
     * @param duplicatelist Map contenant l'id du contact et le numéro correspondant.
     * @return la liste des numéros d'un contact sans doublons.
     */
    public static List<com.hg.development.apps.messagenotifier_v1.Contact.Person.Number> removeNumbersDuplicated(Map<String,Number> duplicatelist){
        try {
            List<com.hg.development.apps.messagenotifier_v1.Contact.Person.Number> list = new ArrayList<>();

            Set set = duplicatelist.entrySet();
            Iterator i = set.iterator();

            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();

                Number number = (Number) me.getValue();

                if(!list.contains(number))
                    list.add((Number) me.getValue());
            }

            return list;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de supprimer les doublons dans la liste des contacts.
     * @param duplicatelist Map contenant le nom de chaque contact.
     * @return la liste des contacts sans doublons.
     */
    public static List<LocalContact> removeContactsDuplicated(Map<String,LocalContact> duplicatelist) {
        try {
            List<LocalContact> list = new ArrayList<>();

            Set set = duplicatelist.entrySet();
            Iterator i = set.iterator();

            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();

                LocalContact contact = (LocalContact) me.getValue();

                if(!list.contains((contact)))
                    list.add(contact);
            }

            return list;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de convertir une photo d'un contact à un objet String.
     * @param bitmap photo du contact à convertir.
     * @return
     */
    public static String BitMapToString(Bitmap bitmap){
        try {
            ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
            byte[] b = ByteStream.toByteArray();
            String temp = Base64.encodeToString(b, Base64.DEFAULT);

            return temp;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     *
     * @return adresse IP v4 actuelle du téléphone.
     * @throws SocketException
     */
    private String getLocalIpAddress() throws SocketException
    {
        String ipv4 = null;
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

            if(interfaces.size() > 0){
                for (NetworkInterface ni : interfaces)
                {
                    List<InetAddress>  ialist = Collections.list(ni.getInetAddresses());

                    if(ialist.size()>0)
                    {
                        for (InetAddress address: ialist)
                        {
                            if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = address.getHostAddress()));
                        }
                    }

                }
            }

        }
        catch (SocketException ex)
        {
            throw ex;
        }
        finally
        {
            return  ipv4;
        }
    }
}
