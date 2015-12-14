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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import gurux.dlms.GXByteBuffer;
import gurux.dlms.GXDLMSClient;
import gurux.dlms.GXDLMSSettings;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ImageTransferStatus;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;

public class GXDLMSImageTransfer extends GXDLMSObject implements IGXDLMSBase {
    private long imageSize;
    private Dictionary<Long, Object> imageData = new Hashtable<Long, Object>();
    private long imageBlockSize;
    private String imageTransferredBlocksStatus;
    private long imageFirstNotTransferredBlockNumber;
    private boolean imageTransferEnabled;
    private ImageTransferStatus imageTransferStatus;
    private GXDLMSImageActivateInfo[] imageActivateInfo;

    /**
     * Constructor.
     */
    public GXDLMSImageTransfer() {
        super(ObjectType.IMAGE_TRANSFER, "0.0.44.0.0.255", 0);
        imageBlockSize = 200;
        imageFirstNotTransferredBlockNumber = 0;
        imageTransferEnabled = true;
        GXDLMSImageActivateInfo info = new GXDLMSImageActivateInfo();
        imageActivateInfo = new GXDLMSImageActivateInfo[] { info };
        imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_NOT_INITIATED;
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     */
    public GXDLMSImageTransfer(final String ln) {
        super(ObjectType.IMAGE_TRANSFER, ln, 0);
        imageBlockSize = 200;
        imageFirstNotTransferredBlockNumber = 0;
        imageTransferEnabled = true;
        GXDLMSImageActivateInfo info = new GXDLMSImageActivateInfo();
        imageActivateInfo = new GXDLMSImageActivateInfo[] { info };
        imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_NOT_INITIATED;
    }

    /**
     * Constructor.
     * 
     * @param ln
     *            Logical Name of the object.
     * @param sn
     *            Short Name of the object.
     */
    public GXDLMSImageTransfer(final String ln, final int sn) {
        super(ObjectType.IMAGE_TRANSFER, ln, sn);
        imageBlockSize = 200;
        imageFirstNotTransferredBlockNumber = 0;
        imageTransferEnabled = true;
        GXDLMSImageActivateInfo info = new GXDLMSImageActivateInfo();
        imageActivateInfo = new GXDLMSImageActivateInfo[] { info };
        imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_NOT_INITIATED;
    }

    /**
     * @return Holds the ImageBlockSize, expressed in octets, which can be
     *         handled by the server.
     */
    public final long getImageBlockSize() {
        return imageBlockSize;
    }

    /**
     * @param value
     *            Holds the ImageBlockSize, expressed in octets, which can be
     *            handled by the server.
     */
    public final void setImageBlockSize(final long value) {
        imageBlockSize = value;
    }

    /**
     * @return Provides information about the transfer status of each
     *         ImageBlock. Each bit in the bit-string provides information about
     *         one individual ImageBlock.
     */
    public final String getImageTransferredBlocksStatus() {
        return imageTransferredBlocksStatus;
    }

    /**
     * @param value
     *            Provides information about the transfer status of each
     *            ImageBlock. Each bit in the bit-string provides information
     *            about one individual ImageBlock.
     */
    public final void setImageTransferredBlocksStatus(final String value) {
        imageTransferredBlocksStatus = value;
    }

    /**
     * @return Provides the ImageBlockNumber of the first ImageBlock not
     *         transferred. NOTE If the Image is complete, the value returned
     *         should be above the number of blocks calculated from the Image
     *         size and the ImageBlockSize
     */
    public final long getImageFirstNotTransferredBlockNumber() {
        return imageFirstNotTransferredBlockNumber;
    }

    /**
     * @param value
     *            Provides the ImageBlockNumber of the first ImageBlock not
     *            transferred. NOTE If the Image is complete, the value returned
     *            should be above the number of blocks calculated from the Image
     *            size and the ImageBlockSize
     */
    public final void setImageFirstNotTransferredBlockNumber(final long value) {
        imageFirstNotTransferredBlockNumber = value;
    }

    /**
     * @return Controls enabling the Image transfer process. The method can be
     *         invoked successfully only if the value of this attribute is true.
     */
    public final boolean getImageTransferEnabled() {
        return imageTransferEnabled;
    }

    /**
     * @param value
     *            Controls enabling the Image transfer process. The method can
     *            be invoked successfully only if the value of this attribute is
     *            true.
     */
    public final void setImageTransferEnabled(final boolean value) {
        imageTransferEnabled = value;
    }

    /**
     * @return Holds the status of the Image transfer process.
     */
    public final ImageTransferStatus getImageTransferStatus() {
        return imageTransferStatus;
    }

    /**
     * @param value
     *            Holds the status of the Image transfer process.
     */
    public final void setImageTransferStatus(final ImageTransferStatus value) {
        imageTransferStatus = value;
    }

    public final GXDLMSImageActivateInfo[] getImageActivateInfo() {
        return imageActivateInfo;
    }

    public final void
            setImageActivateInfo(final GXDLMSImageActivateInfo[] value) {
        imageActivateInfo = value;
    }

    @Override
    public final Object[] getValues() {
        return new Object[] { getLogicalName(), getImageBlockSize(),
                getImageTransferredBlocksStatus(),
                getImageFirstNotTransferredBlockNumber(),
                getImageTransferEnabled(), getImageTransferStatus(),
                getImageActivateInfo() };
    }

    @Override
    public final int[] getAttributeIndexToRead() {
        java.util.ArrayList<Integer> attributes =
                new java.util.ArrayList<Integer>();
        // LN is static and read only once.
        if (getLogicalName() == null || getLogicalName().compareTo("") == 0) {
            attributes.add(1);
        }
        // ImageBlockSize
        if (!isRead(2)) {
            attributes.add(2);
        }
        // ImageTransferredBlocksStatus
        if (!isRead(3)) {
            attributes.add(3);
        }
        // ImageFirstNotTransferredBlockNumber
        if (!isRead(4)) {
            attributes.add(4);
        }
        // ImageTransferEnabled
        if (!isRead(5)) {
            attributes.add(5);
        }
        // ImageTransferStatus
        if (!isRead(6)) {
            attributes.add(6);
        }
        // ImageActivateInfo
        if (!isRead(7)) {
            attributes.add(7);
        }
        return GXDLMSObjectHelpers.toIntArray(attributes);
    }

    @Override
    public final int getAttributeCount() {
        return 7;
    }

    @Override
    public final int getMethodCount() {
        return 4;
    }

    @Override
    public final byte[] invoke(final GXDLMSSettings settings, final int index,
            final Object parameters) {
        imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_NOT_INITIATED;
        // Image transfer initiate
        if (index == 1) {
            imageFirstNotTransferredBlockNumber = 0;
            imageTransferredBlocksStatus = "";
            Object[] value = (Object[]) parameters;
            String imageIdentifier = new String((byte[]) value[0]);
            imageSize = ((Number) value[1]).longValue();
            imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_INITIATED;
            List<GXDLMSImageActivateInfo> list =
                    new ArrayList<GXDLMSImageActivateInfo>();
            list.addAll(Arrays.asList(imageActivateInfo));
            GXDLMSImageActivateInfo item = new GXDLMSImageActivateInfo();
            item.setSize(imageSize);
            item.setIdentification(imageIdentifier);
            list.add(item);
            imageActivateInfo =
                    list.toArray(new GXDLMSImageActivateInfo[list.size()]);
            StringBuilder sb = new StringBuilder();
            for (long pos = 0; pos < imageSize; ++pos) {
                sb.append('0');
            }
            imageTransferredBlocksStatus = sb.toString();
            return new byte[] { 0 };
        } else if (index == 2) {
            // Image block transfer
            Object[] value = (Object[]) parameters;
            long imageIndex = ((Number) value[0]).longValue();
            byte[] tmp = imageTransferredBlocksStatus.getBytes();
            tmp[(int) imageIndex] = '1';
            imageTransferredBlocksStatus = new String(tmp);
            imageFirstNotTransferredBlockNumber = imageIndex + 1;
            imageData.put(imageIndex, (byte[]) value[1]);
            imageTransferStatus = ImageTransferStatus.IMAGE_TRANSFER_INITIATED;
            return new byte[] { 0 };
        } else if (index == 3) {
            // Image verify
            imageTransferStatus =
                    ImageTransferStatus.IMAGE_VERIFICATION_INITIATED;
            // Check that size match.
            int size = 0;
            Enumeration<Object> keys = imageData.elements();
            byte[] value;
            while (keys.hasMoreElements()) {
                value = (byte[]) keys.nextElement();
                size += value.length;
            }
            if (size != imageSize) {
                // Return HW error.
                imageTransferStatus =
                        ImageTransferStatus.IMAGE_VERIFICATION_FAILED;
                throw new RuntimeException("Invalid image size.");
            }
            imageTransferStatus =
                    ImageTransferStatus.IMAGE_VERIFICATION_SUCCESSFUL;
            return new byte[] { 0 };
        } else if (index == 4) {
            // Image activate.
            imageTransferStatus =
                    ImageTransferStatus.IMAGE_ACTIVATION_SUCCESSFUL;
            return new byte[] { 0 };
        } else {
            throw new RuntimeException(
                    "Invoke failed. Invalid attribute index.");
        }
    }

    @Override
    public final DataType getDataType(final int index) {
        if (index == 1) {
            return DataType.OCTET_STRING;
        }
        if (index == 2) {
            return DataType.UINT32;
        }
        if (index == 3) {
            return DataType.BITSTRING;
        }
        if (index == 4) {
            return DataType.UINT32;
        }
        if (index == 5) {
            return DataType.BOOLEAN;
        }
        if (index == 6) {
            return DataType.ENUM;
        }
        if (index == 7) {
            return DataType.ARRAY;
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
            return getImageBlockSize();
        }
        if (index == 3) {
            return imageTransferredBlocksStatus;
        }
        if (index == 4) {
            return getImageFirstNotTransferredBlockNumber();
        }
        if (index == 5) {
            return getImageTransferEnabled();

        }
        if (index == 6) {
            return getImageTransferStatus().ordinal();
        }
        if (index == 7) {
            GXByteBuffer data = new GXByteBuffer();
            data.setUInt8((byte) DataType.ARRAY.getValue());
            data.setUInt8((byte) imageActivateInfo.length); // Count
            for (GXDLMSImageActivateInfo it : imageActivateInfo) {
                data.setUInt8((byte) DataType.STRUCTURE.getValue());
                // Item count.
                data.setUInt8((byte) 3);
                GXCommon.setData(data, DataType.UINT32, it.getSize());
                GXCommon.setData(data, DataType.OCTET_STRING,
                        GXCommon.getBytes(it.getIdentification()));
                String tmp = it.getSignature();
                if (tmp != null) {
                    GXCommon.setData(data, DataType.OCTET_STRING,
                            GXCommon.getBytes(it.getSignature()));
                } else {
                    GXCommon.setData(data, DataType.OCTET_STRING, null);
                }
            }
            return data.array();
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
            if (value == null) {
                setImageBlockSize(0);
            } else {
                setImageBlockSize(((Number) value).intValue());
            }
        } else if (index == 3) {
            if (value == null) {
                imageTransferredBlocksStatus = "";
            } else {
                imageTransferredBlocksStatus = value.toString();
            }
        } else if (index == 4) {
            if (value == null) {
                setImageFirstNotTransferredBlockNumber(0);
            } else {
                setImageFirstNotTransferredBlockNumber(
                        ((Number) value).intValue());
            }
        } else if (index == 5) {
            if (value == null) {
                setImageTransferEnabled(false);
            } else {
                setImageTransferEnabled((Boolean) value);
            }
        } else if (index == 6) {
            if (value == null) {
                setImageTransferStatus(
                        ImageTransferStatus.IMAGE_TRANSFER_NOT_INITIATED);
            } else {
                setImageTransferStatus(ImageTransferStatus
                        .values()[((Number) value).intValue()]);
            }
        } else if (index == 7) {
            imageActivateInfo = new GXDLMSImageActivateInfo[0];
            if (value != null) {
                List<GXDLMSImageActivateInfo> list =
                        new ArrayList<GXDLMSImageActivateInfo>();
                for (Object it : (Object[]) value) {
                    GXDLMSImageActivateInfo item =
                            new GXDLMSImageActivateInfo();
                    item.setSize(((Number) Array.get(it, 0)).longValue());
                    item.setIdentification(
                            new String((byte[]) Array.get(it, 1)));
                    item.setSignature(new String((byte[]) Array.get(it, 2)));
                    list.add(item);
                }
                imageActivateInfo =
                        list.toArray(new GXDLMSImageActivateInfo[list.size()]);
            }
        } else {
            throw new IllegalArgumentException(
                    "GetValue failed. Invalid attribute index.");
        }
    }

    public final byte[][] imageTransferInitiate(final GXDLMSClient client,
            final String imageIdentifier, final long forImageSize) {
        if (imageBlockSize == 0) {
            throw new RuntimeException("Invalid image block size");
        }
        GXByteBuffer data = new GXByteBuffer();
        data.setUInt8(DataType.STRUCTURE.getValue());
        data.setUInt8(2);
        GXCommon.setData(data, DataType.OCTET_STRING,
                GXCommon.getBytes(imageIdentifier));
        GXCommon.setData(data, DataType.UINT32, forImageSize);
        return client.method(this, 1, data.array(), DataType.ARRAY);
    }

    public final byte[][] imageBlockTransfer(final GXDLMSClient client,
            final byte[] imageBlockValue, final int[] imageBlockCount) {
        int cnt = (int) (imageBlockValue.length / imageBlockSize);
        if (imageBlockValue.length % imageBlockSize != 0) {
            ++cnt;
        }
        if (imageBlockCount != null) {
            imageBlockCount[0] = cnt;
        }
        List<byte[]> packets = new ArrayList<byte[]>();
        for (int pos = 0; pos != cnt; ++pos) {
            GXByteBuffer data = new GXByteBuffer();
            data.setUInt8(DataType.STRUCTURE.getValue());
            data.setUInt8(2);
            GXCommon.setData(data, DataType.UINT32, pos);
            byte[] tmp;
            int bytes = (int) (imageBlockValue.length
                    - ((pos + 1) * imageBlockSize));
            // If last packet
            if (bytes < 0) {
                bytes = (int) (imageBlockValue.length - (pos * imageBlockSize));
                tmp = new byte[bytes];
                System.arraycopy(imageBlockValue, (int) (pos * imageBlockSize),
                        tmp, 0, bytes);
            } else {
                tmp = new byte[(int) imageBlockSize];
                System.arraycopy(imageBlockValue, (int) (pos * imageBlockSize),
                        tmp, 0, (int) imageBlockSize);
            }
            GXCommon.setData(data, DataType.OCTET_STRING, tmp);
            packets.addAll(Arrays.asList(
                    client.method(this, 2, data.array(), DataType.ARRAY)));
        }
        byte[][] tmp = new byte[packets.size()][];
        int pos = -1;
        for (byte[] it : packets) {
            ++pos;
            tmp[pos] = new byte[it.length];
            System.arraycopy(it, 0, tmp[pos], 0, it.length);
        }
        return tmp;

    }

    public final byte[][] imageVerify(final GXDLMSClient client) {
        return client.method(this, 3, 0, DataType.INT8);
    }

    public final byte[][] imageActivate(final GXDLMSClient client) {
        return client.method(this, 4, 0, DataType.INT8);
    }
}