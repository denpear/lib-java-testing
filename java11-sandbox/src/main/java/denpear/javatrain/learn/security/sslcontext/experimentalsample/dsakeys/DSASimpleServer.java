package denpear.javatrain.learn.security.sslcontext.experimentalsample.dsakeys;

import denpear.javatrain.common.utils.Utilties;
import denpear.javatrain.learn.security.sslcontext.PEMImporter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import static denpear.javatrain.learn.security.sslcontext.PEMImporter.getKeyAlgorithm;

//https://xakep.ru/2015/08/14/log-almighty/ - посмотреть!
public class DSASimpleServer {
    static void startServer (int port) throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, KeyManagementException {
        final File privateKeyFilePath = new File(Utilties.getContextPath("sslServerSideDSA/server_private_key_folder/keyDSAServer.pem"));
        final File certificateFilePath = new File(Utilties.getContextPath("sslServerSideDSA/server_certificate_folder/certDSAServer.pem"));
        SSLServerSocketFactory factory = PEMImporter.createSSLServerSocketFactory(privateKeyFilePath, certificateFilePath,"password");
        String keyAlgo = getKeyAlgorithm(Utilties.getContextPath("sslServerSideDSA/server_private_key_folder/keyDSAServer.pem"));
        String keyAlgo1 = getKeyAlgorithm(Utilties.getContextPath("sslServerSideRSA/server_private_key_folder/keyServer.pem"));
        try (ServerSocket listener = factory.createServerSocket(port)) {
        //    ((SSLServerSocket) listener).setNeedClientAuth(true);
            ((SSLServerSocket) listener).setEnabledCipherSuites(
                    new String[] { "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384",
                            "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
                            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
                            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_RSA_WITH_AES_256_CBC_SHA256",
                            "TLS_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
                            //пять шифров, которые поддерживает TLS 1.3:
                            "TLS_AES_128_GCM_SHA256",
                            "TLS_AES_256_GCM_SHA384",
//                            "TLS_CHACHA20_POLY1305_SHA256", //Unsupported CipherSuite
                            //                          "TLS_AES_128_CCM_SHA256", //Unsupported CipherSuite
                            //                   "TLS_AES_128_CCM_8_SHA256" //Unsupported CipherSuite
                    });
            ((SSLServerSocket) listener).setEnabledProtocols(
              new String[] { "TLSv1","TLSv1.1","TLSv1.2","TLSv1.3"});
            while (true) {
                try (Socket socket = listener.accept()) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hello World!");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        startServer(8443);
    }
}