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
package com.hg.development.apps.messagenotifier_v1.Contact;

import com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact;
import com.hg.development.apps.messagenotifier_v1.Contact.Person.PhoneContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Class : Contact_Implement
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public abstract class Contact_Implement implements IContact_Provider {
    protected boolean m_bRunCardDetection = true;

    private static final String GET_NUMBER_PROCESS = "Récupération des numéros";
    private static final String GET_PHOTO_PROCESS = "Récupération des images";

    public abstract void ListContacts();
    public abstract List<LocalContact> getContacts();

    /**
     * Permet de récupérer le (ou les) numéro(s) de téléphone d'un contact.
     */
    protected abstract void GetPhoneNumberContact(PhoneContact contact) throws Exception;

    private List<Thread> process = null;

    /**
     * Destructeur : Permet d'arrêter les processus lors de la destruction de cet objet.
     * @throws Exception
     */
    public void finalize() throws Exception {
        try
        {
            ArreterProcess(10);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de savoir si le processus est en cours ou pas.
     * @return
     */
    public boolean ProcessEnCours()
    {
        if(process != null)
        {
            for(Thread thread : process)
            {
                if(thread == null)
                    return false;
                return thread.isAlive();
            }
        }

        return false;
    }

    /**
     * Permet de lancer le processus GetPhoneNumberContact.
     * @param contact
     */
    protected void Lancer_GetPhoneNumberContact(final Object contact)
    {
        try
        {
            if(process == null)
                process = new ArrayList<Thread>();

            Thread th = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        GetPhoneNumberContact((PhoneContact)contact);
                    }
                    catch (Exception ex)
                    {
                        //les erreurs sont traitées dans les méthodes respectives.
                    }
                }
            });

            th.start();

            process.add(th);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet d'arrêter ou de forcer l'arrêt du processus de récupérations.
     * @param timeout
     * @throws Exception
     */
    public void ArreterProcess(int timeout) throws Exception
    {
        try
        {
            if(process != null)
            {
                m_bRunCardDetection = false;


                for(Thread thread : process)
                {
                    for(int time = 0 ; time <timeout; time++)
                    {
                        if(time == timeout)
                        {
                            thread.interrupt();
                            break;
                        }

                        Thread.sleep(100);

                        if(thread.getState() == Thread.State.TERMINATED)
                            break;
                    }
                }
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
