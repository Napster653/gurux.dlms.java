//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.objects;

import gurux.dlms.GXDLMSSettings;
import gurux.dlms.enums.BaudRate;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ObjectType;

public class GXDLMSHdlcSetup extends GXDLMSObject implements IGXDLMSBase {
    private int inactivityTimeout;
    private int deviceAddress;
    private int maximumInfoLengthTransmit;
    private BaudRate communicationSpeed;
    private int windowSizeTransmit;
    private int windowSizeReceive;
    private int interCharachterTimeout;
    private int maximumInfoLengthReceive;

    /**
     * Constructor.
     */
    public GXDLMSHdlcSetup() {
        super(ObjectType.IEC_HDLC_SETUP);
        setCommunicationSpeed(BaudRate.BAUDRATE_9600);
        setWindowSizeTransmit(1);
        setWindowSizeReceive(getWindowSizeTransmit());
        setMaximumInfoLengthReceive(128);
        setMaximumInfoLengthTransmit(getMaximumInfoLengthReceive());
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     */
    public GXDLMSHdlcSetup(final String ln) {
        super(ObjectType.IEC_HDLC_SETUP, ln, 0);
        setCommunicationSpeed(BaudRate.BAUDRATE_9600);
        setWindowSizeTransmit(1);
        setWindowSizeReceive(getWindowSizeTransmit());
        setMaximumInfoLengthReceive(128);
        setMaximumInfoLengthTransmit(getMaximumInfoLengthReceive());
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     * @param sn
     *            Short Name of the object.
     */
    public GXDLMSHdlcSetup(final String ln, final int sn) {
        super(ObjectType.IEC_HDLC_SETUP, ln, 0);
        setCommunicationSpeed(BaudRate.BAUDRATE_9600);
        setWindowSizeTransmit(1);
        setWindowSizeReceive(getWindowSizeTransmit());
        setMaximumInfoLengthReceive(128);
        setMaximumInfoLengthTransmit(getMaximumInfoLengthReceive());
    }

    public final BaudRate getCommunicationSpeed() {
        return communicationSpeed;
    }

    public final void setCommunicationSpeed(final BaudRate value) {
        communicationSpeed = value;
    }

    public final int getWindowSizeTransmit() {
        return windowSizeTransmit;
    }

    public final void setWindowSizeTransmit(final int value) {
        windowSizeTransmit = value;
    }

    public final int getWindowSizeReceive() {
        return windowSizeReceive;
    }

    public final void setWindowSizeReceive(final int value) {
        windowSizeReceive = value;
    }

    public final int getMaximumInfoLengthTransmit() {
        return maximumInfoLengthTransmit;
    }

    public final void setMaximumInfoLengthTransmit(final int value) {
        maximumInfoLengthTransmit = value;
    }

    public final int getMaximumInfoLengthReceive() {
        return maximumInfoLengthReceive;
    }

    public final void setMaximumInfoLengthReceive(final int value) {
        maximumInfoLengthReceive = value;
    }

    public final int getInterCharachterTimeout() {
        return interCharachterTimeout;
    }

    public final void setInterCharachterTimeout(final int value) {
        interCharachterTimeout = value;
    }

    public final int getInactivityTimeout() {
        return inactivityTimeout;
    }

    public final void setInactivityTimeout(final int value) {
        inactivityTimeout = value;
    }

    public final int getDeviceAddress() {
        return deviceAddress;
    }

    public final void setDeviceAddress(final int value) {
        deviceAddress = value;
    }

    @Override
    public final Object[] getValues() {
        return new Object[] { getLogicalName(), getCommunicationSpeed(),
                getWindowSizeTransmit(), getWindowSizeReceive(),
                getMaximumInfoLengthTransmit(), getMaximumInfoLengthReceive(),
                getInterCharachterTimeout(), getInactivityTimeout(),
                getDeviceAddress() };
    }

    /*
     * Returns collection of attributes to read. If attribute is static and
     * already read or device is returned HW error it is not returned.
     */
    @Override
    public final int[] getAttributeIndexToRead() {
        java.util.ArrayList<Integer> attributes =
                new java.util.ArrayList<Integer>();
        // LN is static and read only once.
        if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
            attributes.add(1);
        }
        // CommunicationSpeed
        if (!isRead(2)) {
            attributes.add(2);
        }
        // WindowSizeTransmit
        if (!isRead(3)) {
            attributes.add(3);
        }
        // WindowSizeReceive
        if (!isRead(4)) {
            attributes.add(4);
        }
        // MaximumInfoLengthTransmit
        if (!isRead(5)) {
            attributes.add(5);
        }
        // MaximumInfoLengthReceive
        if (!isRead(6)) {
            attributes.add(6);
        }
        // InterCharachterTimeout
        if (!isRead(7)) {
            attributes.add(7);
        }
        // InactivityTimeout
        if (!isRead(8)) {
            attributes.add(8);
        }
        // DeviceAddress
        if (!isRead(9)) {
            attributes.add(9);
        }
        return GXDLMSObjectHelpers.toIntArray(attributes);
    }

    /*
     * Returns amount of attributes.
     */
    @Override
    public final int getAttributeCount() {
        return 9;
    }

    /*
     * Returns amount of methods.
     */
    @Override
    public final int getMethodCount() {
        return 0;
    }

    @Override
    public final DataType getDataType(final int index) {
        if (index == 1) {
            return DataType.OCTET_STRING;
        }
        if (index == 2) {
            return DataType.ENUM;
        }
        if (index == 3) {
            return DataType.UINT8;
        }
        if (index == 4) {
            return DataType.UINT8;
        }
        if (index == 5) {
            return DataType.UINT16;
        }
        if (index == 6) {
            return DataType.UINT16;
        }
        if (index == 7) {
            return DataType.UINT16;
        }
        if (index == 8) {
            return DataType.UINT16;
        }
        if (index == 9) {
            return DataType.UINT16;
        }
        throw new IllegalArgumentException(
                "getDataType failed. Invalid attribute index.");
    }

    /*
     * Returns value of given attribute.
     */
    @Override
    public final Object getValue(final GXDLMSSettings settings, final int index,
            final int selector, final Object parameters) {
        if (index == 1) {
            return getLogicalName();
        }
        if (index == 2) {
            return this.communicationSpeed.ordinal();
        }
        if (index == 3) {
            return this.windowSizeTransmit;
        }
        if (index == 4) {
            return this.windowSizeReceive;
        }
        if (index == 5) {
            return this.maximumInfoLengthTransmit;
        }
        if (index == 6) {
            return this.maximumInfoLengthReceive;
        }
        if (index == 7) {
            return interCharachterTimeout;
        }
        if (index == 8) {
            return inactivityTimeout;
        }
        if (index == 9) {
            return deviceAddress;
        }
        throw new IllegalArgumentException(
                "GetValue failed. Invalid attribute index.");
    }

    /*
     * Set value of given attribute.
     */
    @Override
    public final void setValue(final GXDLMSSettings settings, final int index,
            final Object value) {
        if (index == 1) {
            super.setValue(settings, index, value);
        } else if (index == 2) {
            communicationSpeed = BaudRate.values()[((Number) value).intValue()];
        } else if (index == 3) {
            windowSizeTransmit = ((Number) value).intValue();
        } else if (index == 4) {
            windowSizeReceive = ((Number) value).intValue();
        } else if (index == 5) {
            maximumInfoLengthTransmit = ((Number) value).intValue();
        } else if (index == 6) {
            maximumInfoLengthReceive = ((Number) value).intValue();
        } else if (index == 7) {
            interCharachterTimeout = ((Number) value).intValue();
        } else if (index == 8) {
            inactivityTimeout = ((Number) value).intValue();
        } else if (index == 9) {
            deviceAddress = ((Number) value).intValue();
        } else {
            throw new IllegalArgumentException(
                    "GetValue failed. Invalid attribute index.");
        }
    }
}