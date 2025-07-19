package kg.kazbekov.megatesttask.service.external.impl;

import jakarta.annotation.PostConstruct;
import kg.kazbekov.megatesttask.service.external.ExternalApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalApiServiceImpl implements ExternalApiService {
    private final WebClient webClient;

    @Value("${service.external.external-api-service.url}")
    private String url;

    @PostConstruct
    @Override
    public void fetchDataFromExternalApi() {
        log.info("Fetching data from external API.");

        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(body -> log.info("External API response: {}", body))
                .subscribe();
    }
}
