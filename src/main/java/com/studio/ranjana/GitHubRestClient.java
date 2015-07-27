package com.studio.ranjana;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GitHubRestClient {

    private static final String IO_ERROR = "IO Error";
    private static final String CLIENT_PROTOCOL_ERROR = "ClientProtocol Error";
    private static final String INVALID = "Invalid";
    

    public String requestIssues(String username, String password,
            String issueSource) {
        String jsonContent = null;
        HttpHost target = new HttpHost("api.github.com", 443, "https");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(username, password));

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);

        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpGet httpget = new HttpGet(issueSource);

        try {
            CloseableHttpResponse response = httpclient.execute(target,
                    httpget, localContext);
            if (response.getStatusLine().getStatusCode() == 200) {
                
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));

                jsonContent = reader.readLine();

                EntityUtils.consume(entity);
            }
            else {
                jsonContent = INVALID;
            }
        }
        catch (ClientProtocolException e) {
            System.out.println(CLIENT_PROTOCOL_ERROR);
        }
        catch (IOException e) {
            System.out.println(IO_ERROR);
        }
        finally {
            try {
                httpclient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        return jsonContent;
    }

}
