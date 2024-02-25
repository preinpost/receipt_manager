package soo.receipt_writer.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.converter.util.ObjectConverter;
import com.webauthn4j.data.client.Origin;
import com.webauthn4j.metadata.converter.jackson.WebAuthnMetadataJSONModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAuthnConfig {

    @Value("${webauthn.origin}")
    private String origin;
    @Value("${webauthn.rp}")
    private String rp;
    @Value("${webauthn.rpName}")
    private String rpName;

    @Bean
    public ObjectConverter objectConverter() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new WebAuthnMetadataJSONModule());
        ObjectMapper cborMapper = new ObjectMapper(new CBORFactory());
        return new ObjectConverter(jsonMapper, cborMapper);
    }

    @Bean
    public WebAuthnManager webAuthnManager(ObjectConverter objectConverter) {
        return WebAuthnManager.createNonStrictWebAuthnManager(objectConverter);
    }

    @Bean
    WebAuthnProperty webAuthnProperty() {
        return new WebAuthnProperty(
                new Origin(origin),
                rp,
                rpName
        );
    }
}