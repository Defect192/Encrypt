package com.example.encrypt_sms;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Network {

    int mLocalPort=0;

    public void initializeServerSocket() throws IOException {
        ServerSocket mServerSocket = new ServerSocket(0);
        mLocalPort = mServerSocket.getLocalPort();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void registerService(){
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setServiceName(“Phone_Connect”);
        serviceInfo.setServiceType(“Encrypt_SMS.tcp.”);
        serviceInfo.setPort(mLocalPort);

        mNsdManager = Context.getSystemService(
                Context.NSD_SERVICE);

        mNsdManager.registerService(
                serviceInfo,
                NsdManager.PROTOCOL_DNS_SD,
                mRegistrationListener);
    }

    mNsdManager.discoverServices(
            “_myapp._tcp”,
    NsdManager.PROTOCOL_DNS_SD
    mDiscoveryListener);

    List<NsdServiceInfo> mDiscoveredServices = new ArrayList<NsdServiceInfo>()
    mDiscoveryListener = new NdsManager.DiscoveryListener(){

        public void onServiceFound(NsdServiceInfo service){
            if(!service.getServiceType().equals(SERVICE_TYPE)){
                Log.d(TAG, “Unknown Service Type: ”+service.getServiceType());

            } else if (service.getServiceName().equals(mServiceName)){
                Log.d(TAG, “Same machine: “+mServiceName);
            } else {
                mDiscoveredServices.add(service);
            }
        }

        mResolveListener = new NsdManager.ResolveListener(){

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode){
                Log.e(TAG, “Resolve failed” + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo){
                mService = serviceInfo;
                int port= mService.getPort();
                InetAddress host = mService.getHost();
            }

        };

        public void teardown(){
            mNsdManager.unregisterService(mRegistrationListener);
            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
        }




    }
