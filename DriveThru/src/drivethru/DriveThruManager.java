/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package drivethru;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import drivethru.storage.ScoreKeeperDao;
import drivethru.storage.ScoreKeeperDynamoDbClient;
import drivethru.storage.ScoreKeeperGame;
import drivethru.storage.ScoreKeeperGameData;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * The {@link DriveThruManager} receives various events and intents and manages the flow of the
 * game.
 */
public class DriveThruManager {

    private final ScoreKeeperDao scoreKeeperDao;

    public DriveThruManager(final AmazonDynamoDBClient amazonDynamoDbClient) {
        ScoreKeeperDynamoDbClient dynamoDbClient =
                new ScoreKeeperDynamoDbClient(amazonDynamoDbClient);
        scoreKeeperDao = new ScoreKeeperDao(dynamoDbClient);
    }
    
    /**
     * Creates and returns response for Launch request.
     *
     * @param request
     *            {@link LaunchRequest} for this request
     * @param session
     *            Speechlet {@link Session} for this request
     * @return response for launch request
     */
    public SpeechletResponse getLaunchResponse(LaunchRequest request, Session session) {
        // Speak welcome message and ask user questions
        // based on whether there are players or not.
        String speechText, repromptText;

        speechText = "Hello, Welcome to Sata Drive, Whom do I have the pleasure of working with today ?";
        repromptText = "May I know your name ? ";

        return getAskSpeechletResponse(speechText, repromptText);
    }

    public SpeechletResponse getUserNameIntentResponse(Intent intent, Session session, SkillContext skillContext) {
        // Speak welcome message and ask user questions
        String speechText, repromptText;

//        scoreKeeperDao
	    speechText = "Welcome... ? ";
	    repromptText = "Can you please give me your order ?";

        return getAskSpeechletResponse(speechText, repromptText);
    }    
    
    /**
     * Creates and returns response for the help intent.
     *
     * @param intent
     *            {@link Intent} for this request
     * @param session
     *            {@link Session} for this request
     * @param skillContext
     *            {@link SkillContext} for this request
     * @return response for the help intent
     */
    public SpeechletResponse getHelpIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return skillContext.needsMoreHelp() ? getAskSpeechletResponse(
                DriveThruTextUtil.COMPLETE_HELP + " So, how can I help?",
                DriveThruTextUtil.NEXT_HELP)
                : getTellSpeechletResponse(DriveThruTextUtil.COMPLETE_HELP);
    }

    /**
     * Creates and returns response for the exit intent.
     *
     * @param intent
     *            {@link Intent} for this request
     * @param session
     *            {@link Session} for this request
     * @param skillContext
     *            {@link SkillContext} for this request
     * @return response for the exit intent
     */
    public SpeechletResponse getExitIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return skillContext.needsMoreHelp() ? getTellSpeechletResponse("Okay. Whenever you're "
                + "ready, you can start placing your order.")
                : getTellSpeechletResponse("");
    }    

    /**
     * Returns an ask Speechlet response for a speech and reprompt text.
     *
     * @param speechText
     *            Text for speech output
     * @param repromptText
     *            Text for reprompt output
     * @return ask Speechlet response for a speech and reprompt text
     */
    private SpeechletResponse getAskSpeechletResponse(String speechText, String repromptText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    /**
     * Returns a tell Speechlet response for a speech and reprompt text.
     *
     * @param speechText
     *            Text for speech output
     * @return a tell Speechlet response for a speech and reprompt text
     */
    private SpeechletResponse getTellSpeechletResponse(String speechText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

}
