package se.iths.loppis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

public class CreateItemHandler implements RequestHandler<HttpRequest, HttpResponse> {

    @Override
    public HttpResponse handleRequest(HttpRequest httpRequest, Context context) {

        context.getLogger().log("HttpRequest send to PostItemHandler: " + httpRequest.toString());

        // Hämta ut inputdata
        String body = httpRequest.getBody();

        // Omvandla inputdata från String till Item-objekt
        Gson gson = new Gson();
        Item itemToAdd = gson.fromJson(body, Item.class);

        // Koppla ihop med DynamoDB, samt skapa en mapper
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(Item.DYNAMODB_TABLE_NAME))
                .build();
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

        mapper.save(itemToAdd);

        return new HttpResponse("201");
    }
}
