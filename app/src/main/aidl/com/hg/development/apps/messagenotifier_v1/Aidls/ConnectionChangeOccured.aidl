// ConnectionChangeOccured.aidl
package com.hg.development.apps.messagenotifier_v1.Aidls;

// Declare any non-default types here with import statements

interface ConnectionChange {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    public void EventOccured(ConnectionStatus event);
}
