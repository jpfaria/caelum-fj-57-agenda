package br.com.caelum.cadastro.listener;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

public class MyPhoneStateListener extends PhoneStateListener {
	
	private int signalStrength;
	
	@Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength)
    {
       super.onSignalStrengthsChanged(signalStrength);
       this.signalStrength = signalStrength.getGsmSignalStrength();
    }
	
	public int getSignalStrength() {
		return this.signalStrength;
	}
	
}
