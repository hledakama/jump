package org.lhedav.pp.business.model.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Shareable {
	public boolean SendTo( DataOutputStream os  );
    public boolean RecvFrom( DataInputStream is, int aVersion );
    //public boolean SendToCrc( CrcPipedOutputStream pos, int aVersion );
}
