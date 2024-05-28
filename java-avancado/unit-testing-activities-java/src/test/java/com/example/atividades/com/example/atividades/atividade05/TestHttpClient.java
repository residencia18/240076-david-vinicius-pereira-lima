package com.example.atividades.atividade05;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.atividades.atividade05.HttpClient;

import okhttp3.*;

import java.io.IOException;

public class TestHttpClient {
    @Test
    public void testGetDataFromApiSuccess() throws IOException {
        OkHttpClient mockClient = mock(OkHttpClient.class);
        Call mockCall = mock(Call.class);
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://fakeapi.com/data").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create("{\"key\":\"value\"}", MediaType.parse("application/json")))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        HttpClient httpClient = new HttpClient(mockClient);
        String url = "http://fakeapi.com/data";

        String data = httpClient.getDataFromApi(url);
        assertEquals("{\"key\":\"value\"}", data);
        verify(mockClient).newCall(any(Request.class));
    }
}
