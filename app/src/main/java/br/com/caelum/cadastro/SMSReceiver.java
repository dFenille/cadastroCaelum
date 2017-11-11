package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.DAO.AlunoDao;

/**
 * Created by android7087 on 11/11/17.
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "chegou sms", Toast.LENGTH_SHORT).show();
        Bundle bundle = intent.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");
        byte[] mensagem = (byte[]) mensagens[0];
        String formato = (String) bundle.get("format");
        SmsMessage sms = SmsMessage.createFromPdu(mensagem,formato);

        AlunoDao alunoDao = new AlunoDao(context);
        if(alunoDao.isAluno(sms.getDisplayOriginatingAddress())){
            MediaPlayer mp = MediaPlayer.create(context,R.raw.msg);
            mp.start();
        }

    }

}
