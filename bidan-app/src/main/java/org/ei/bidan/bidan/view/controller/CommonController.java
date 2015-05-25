package org.ei.bidan.bidan.view.controller;

import org.ei.bidan.util.StringUtil;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dimas Ciputra on 5/20/15.
 */
public class CommonController {

    public CharSequence[] onRandomNameChars(final SmartRegisterClient client,
                                            List<SmartRegisterClient> clients,
                                            List<String> randomDummyName,
                                            int optionSize) {

        List<String> randomName = new ArrayList<String>();

        // Shuffle clients array
        Collections.shuffle(clients);

        // If clients size array less than option dialog size
        if(clients.size() < optionSize) {
            randomName = randomDummyName.subList(0, optionSize - clients.size());
        }

        // Slice clients list
        clients = clients.subList(0, optionSize - randomName.size());

        // If the sliced clients not contain the current client then add it
        if(!clients.contains(client)) {
            clients =  clients.subList(0, clients.size()-1);
            clients.add(client);
        }

        // Clients list to String list
        for(int i = 0; i < clients.size(); i++) {
            randomName.add(StringUtil.humanize(clients.get(i).name()));
        }

        Collections.shuffle(randomName);

        return randomName.toArray(new CharSequence[randomName.size()]);
    }
}
