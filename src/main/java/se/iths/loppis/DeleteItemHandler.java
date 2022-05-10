package se.iths.loppis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteItemHandler implements RequestHandler<HttpRequest, HttpResponse> {

    @Override
    public HttpResponse handleRequest(HttpRequest httpRequest, Context context) {

        context.getLogger().log("HttpRequest send to DeleteItemHandler: " + httpRequest.toString());

        // Hämta ID från HttpRequest
        String idFromRequest = httpRequest.pathParameters.get("id");

        // Koppla ihop med DynamoDB, samt skapa en mapper
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(Item.DYNAMODB_TABLE_NAME))
                .build();
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

        Item fetchedItem = mapper.load(Item.class, idFromRequest);

        if (fetchedItem == null) {
            context.getLogger().log("No item found with ID: " + idFromRequest);
            return new HttpResponse("404");
        }

        mapper.delete(fetchedItem);

        return new HttpResponse("204");
    }


}
