package se.iths.loppis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class ListItemHandler implements RequestHandler<HttpRequest, HttpResponse> {

    @Override
    public HttpResponse handleRequest(HttpRequest httpRequest, Context context) {

        context.getLogger().log("HttpRequest send to ListItemHandler: " + httpRequest.toString());


        // Koppla ihop med DynamoDB, samt skapa en mapper
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(Item.DYNAMODB_TABLE_NAME))
                .build();
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

        // Scanna hela db-tabellen och returnera alla items
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<Item> results = mapper.scan(Item.class, scanExp);


        return new HttpResponse(results);
    }
}
