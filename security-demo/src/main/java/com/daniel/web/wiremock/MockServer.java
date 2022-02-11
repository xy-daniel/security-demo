package com.daniel.web.wiremock;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * 模拟
 *
 * @author daniel
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        configureFor("175.24.172.160", 8060);
        removeAllMappings();

        stubForMock("/order/1", "01");
        stubForMock("/order/2", "02");
    }

    private static void stubForMock(String path, String fileName) throws IOException {
        Resource resource = new ClassPathResource("mock/response/" + fileName + ".txt");
        String content = FileUtils.readFileToString(resource.getFile(), "UTF-8");
        stubFor(
                get(urlPathEqualTo(path))
                        .willReturn(aResponse().withBody(content).withStatus(200))
        );
    }

}
