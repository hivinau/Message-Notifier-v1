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
package com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile;

import com.hg.development.apps.messagenotifier_v1.Utils.Utility;

/**
 * Class : Profile_Channel
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class Profile_Channel {

    private String IP = null;
    private int PORT = 0;

    /**
     *
     * @return adresse IP du serveur.
     */
    public String getIP() {
        return IP;
    }

    /**
     * Modifie l'adresse IP du serveur.
     * @param IP
     */
    public void setIP(String IP) throws Exception {
        try
        {
            if(Utility.validate_ip(IP))
                this.IP = IP;
        }
        catch (Exception ex)
        {
            throw  ex;
        }
    }

    /**
     *
     * @return le port d'écoute.
     */
    public int getPORT() {
        return PORT;
    }

    /**
     * Modifie le port d'écoute.
     * @param PORT
     */
    public void setPORT(int PORT) throws Exception {
        try
        {
            if(Utility.validate_port(String.valueOf(PORT)))
                this.PORT = PORT;
        }
        catch (Exception ex)
        {
            throw  ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel}.
     *
     */
    public Profile_Channel()
    {
        try
        {

        }
        catch (Exception ex)
        {

        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel}.
     * @param IP Adresse IP du serveur hôte.
     * @throws Exception Le paramètre est invalide.
     */
    public Profile_Channel(String IP) throws Exception {
        try
        {
            if(Utility.validate_ip(IP))
                this.IP = IP;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel}.
     * @param IP Adresse IP du serveur hôte.
     * @param PORT Numéro du port d'écoute sur le réseau.
     * @throws Exception Les paramètres sont invalides.
     */
    public Profile_Channel(String IP, int PORT) throws Exception {
        try
        {
            if(Utility.validate_ip(IP))
                this.IP = IP;

            if(Utility.validate_port(String.valueOf(PORT)))
                this.PORT = PORT;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel}.
     * @param channel Instance contenant les paramètres de connection tels que IP ou PORT...
     * @throws Exception Les paramètres sont invalides.
     */
    public Profile_Channel(Profile_Channel channel) throws Exception {
        try
        {
            if(channel == null)
                throw new IllegalArgumentException("Spécifier les paramètres de connection (IP, PORT, ...)");

            if(Utility.validate_ip(channel.getIP()))
                this.IP = channel.getIP();

            if(Utility.validate_port(String.valueOf(channel.getPORT())))
                this.PORT = channel.getPORT();
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
