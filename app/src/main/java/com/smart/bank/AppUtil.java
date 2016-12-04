package com.smart.bank;

import com.smart.bank.vo.BankVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purushoy on 11/20/2016.
 */

public class AppUtil {
    public static final String BALANCECHECK_PREF = "BalanceCheckPref";

    public static List<BankVO> prepareBankData() {
        List<BankVO> voList = new ArrayList<>(100);
        BankVO vo = new BankVO("*99*41", "State Bank of India", "sbi.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*43", "HDFC Bank", "hdfc_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*44", "ICICI Bank", "icici_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*45", "AXIS Bank", "axis_bank.gif", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*42", " Punjab National Bank", "punjab_national_bank.gif", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*46", "Canara Bank", "canara_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*47", " Bank Of India", "boi_bank.png", "", "", "", "",false);
        voList.add(vo);
        vo = new BankVO("*99*48", "Bank of Baroda", "bob.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*49", "IDBI Bank", "idbi_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*50", "Union Bank of India", "union_bank_of_india.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*51", "Central Bank of India", "central_bank_of_india.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*52", "India Overseas Bank", "indian_overseas_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*53", "Oriental Bank of Commerce", "oriental_bank_of_commerce.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*54", "Allahabad Bank", "allahabad_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*55", "Syndicate Bank", "syndicate_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*56", "UCO Bank", "uco_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*57", "Corporation Bank", "corporation_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*58", " Indian Bank", "indian_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*59", "Andhra Bank", "andhra_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*60", " State Bank Of Hyderabad", "sbh.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*61", " Bank of Maharashtra ", "bank_of_maharastra.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*62", " State Bank of Patiala", "sbp.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*63", " United Bank of India", "united_bank_of_india.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*64", "Vijaya Bank", "vijaya_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*65", "Dena Bank", "dena_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*66", "Yes Bank", "yes_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*67", "State Bank of Travancore", "sbt.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*68", "Kotak Mahindra Bank", "kotak_mahindra_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*69", "IndusInd Bank", "indus_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*70", " State Bank of Bikaner and Jaipur ", "sbbj.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*71", " Punjab and Sind Bank", "punjab_sind_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*72", "Federal Bank", "federal_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*73", "State Bank of Mysore", "state_bank_of_mysore.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*75", "Karur Vysya Bank", "karur_vysya_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*76", "Karnataka Bank", "karnataka_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*77", "Tamilnad Mercantile Bank", "tmb.png", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*78", "DCB Bank", "dcb_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*79", " Ratnakar Bank", "rbl_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*80", "Nainital Bank", "nainital_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*81", "Janata Sahakari Bank", "janata_sahakari_bank.gif", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*82", "Mehsana Urban Co-Operative Bank", "mucb.png", "", "", "", "", false);
        voList.add(vo);
        /*vo = new BankVO("*99*83", "NKGSB Bank", "", "", "", "", "",false);
        voList.add(vo);*/
        vo = new BankVO("*99*84", "Saraswat Bank", "saraswat_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        /*vo = new BankVO("*99*85", "Apna Sahakari Bank", "", "", "", "", "",false);
        voList.add(vo);*/
        vo = new BankVO("*99*86", "Bhartiya Mahila Bank", "bmb.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*87", " Abhyudaya Co-Operative Bank", "abob.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*88", "Punjab & Maharashtra Co-Operative Bank", "pmc_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        /*vo = new BankVO("*99*89", "Hasti Co-Operative Bank", "", "", "", "", "",false);
        voList.add(vo);*/
        vo = new BankVO("*99*90", " Gujarat State Co-Operative Bank", "gsc_bank.jpg", "", "", "", "", false);
        voList.add(vo);
        vo = new BankVO("*99*91", " Kalupur Commercial Co-Operative Bank", "kccb.png", "", "", "", "", false);
        voList.add(vo);
        return voList;
    }
}
