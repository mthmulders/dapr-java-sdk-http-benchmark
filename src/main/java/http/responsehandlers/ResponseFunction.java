package http.responsehandlers;

import http.DaprHttp;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ResponseFunction implements Function<HttpResponse<byte[]>, DaprHttp.Response> {

    @Override
    public DaprHttp.Response apply(HttpResponse<byte[]> response) {

            Map<String, String> mapHeaders = new HashMap<>();
            byte[] result = response.body();
            response.headers().map().forEach((key, value) -> {
                Optional.ofNullable(value).orElse(Collections.emptyList()).forEach(urlParameterValue -> {
                    mapHeaders.put(key, urlParameterValue);
                });
            });

            return new DaprHttp.Response(result, mapHeaders, response.statusCode());
    }
}
