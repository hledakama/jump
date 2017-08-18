package org.lhedav.pp.persistence.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import org.lhedav.pp.persistence.common.Shareable;

public class AdConfiguration implements Shareable, Serializable{

	@Override
	public boolean SendTo(DataOutputStream os) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RecvFrom(DataInputStream is, int aVersion) {
		// TODO Auto-generated method stub
		return false;
	}

}
