package thinkbiz.solutions.tbs.com.CardDetailsPkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 28-Jun-18.
 */

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("The phone comes in three different storage capacities – 32 GB, 128 GB and 256 GB along with 3 GB RAM that you can pick as per your needs. But there is no microSD card support to expand the memory.");

        List<String> list2 = new ArrayList<String>();
        list2.add("It is equipped with a pair of Lightning EarPods that you can use for listening to your favorite music albums or watching videos.");

        List<String> list3 = new ArrayList<String>();
        list3.add("Yes, the phone comes equipped with dual improved dual cameras on the rear. With large camera overhaul, you can click brilliant shots and make exclusive videos. It also has a good image signal processor which utilizes machine learning in order to make the images look amazing.");

        List<String> list4 = new ArrayList<String>();
        list4.add("You will not find much difference in their software, specifications and design. But the main difference is the building material as compared to other variants. The phone is constructed from aluminum and glass. Also, it looks smooth and shiny than ever.");

        List<String> list5 = new ArrayList<String>();
        list5.add("Yes, it comes with a fingerprint sensor that will facilitate you to access the handset in an easy way. By using this feature, you don't have to remember any password or pattern.");

        List<String> list6 = new ArrayList<String>();
        list6.add("The phone supports NFC feature that will permit you to make your payments in a secure way. It will let you to pay through Apple Pay which is Visa, MasterCard and AMEX certified.");

        List<String> list7 = new ArrayList<String>();
        list7.add("It comes equipped with a professional camera that can record 4K videos. Some top features of its camera are wide color capture for live videos and photos, body and face detection, Six element lens, OIS, Hybrid IR filter and more.");



        expandableListDetail.put("What is the storage capacity of Apple iPhone 7 Plus? Can we expand it by using a dedicated MicroSD card?", list1);
        expandableListDetail.put("How can I listen to my favorite music or videos over the phone?", list2);
        expandableListDetail.put("Does the Apple iPhone 7 Plus come with improved cameras?", list3);
        expandableListDetail.put("What is the main specialty in the Apple phone 7 and 7 Plus Jet Black variant?", list4);
        expandableListDetail.put("Does the phone support a fingerprint sensor?", list5);
        expandableListDetail.put("Does the Apple iPhone 7 Plus come with NFC feature?", list6);
        expandableListDetail.put("What are the main features of iPhone 7 Plus camera?", list7);
        return expandableListDetail;
    }

}
