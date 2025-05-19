package org.mk.graphql;


import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClientBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mk.graphql.model.User;

import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Argument.arg;
import static io.smallrye.graphql.client.core.Argument.args;
import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

@ApplicationScoped
public class GQLClientUsage {

    @ConfigProperty(name = "gql-url")
    String gqlUrl;

    DynamicGraphQLClient client;

    @PostConstruct
    void init(){
         client = DynamicGraphQLClientBuilder.newBuilder()
                .url(gqlUrl)
                .build();
    }

    public User executeGetUser(String id) {
        Document document = document(
                operation(field("user",
                        args(arg("id", id)),
                        field("id"),
                        field("name"),
                        field("email"),
                        field("phone"),
                        field("website"),
                        field("address",
                                field("street"), field("city"), field("zipcode"))
                )));
        Response response;
        try {
            response = client.executeSync(document);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.getObject(User.class, "user");
    }
}
