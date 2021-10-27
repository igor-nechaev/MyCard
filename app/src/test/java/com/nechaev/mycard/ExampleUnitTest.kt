package com.nechaev.mycard

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import com.nechaev.mycard.model.ValueDeserializer
import com.nechaev.mycard.model.ValueInfo
import org.junit.Test
import com.nechaev.mycard.model.ValueInfoDeserializer

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun valueInfo_deserializer(){
        val json_string : String = "{\n" +
                "            \"ID\": \"R01010\",\n" +
                "            \"NumCode\": \"036\",\n" +
                "            \"CharCode\": \"AUD\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Австралийский доллар\",\n" +
                "            \"Value\": 52.6219,\n" +
                "            \"Previous\": 53.1396\n" +
                "}"


        val jsonElement : JsonElement = JsonParser().parse(json_string)

        val res = ValueInfoDeserializer().deserialize(jsonElement, null, null)

        //Log.d(res.toString(), "nechaev");

        assertEquals("  f ", res.toString())

    }

    @Test
    fun value_deserializer(){
        val json_string : String = "{\n" +
                "    \"Date\": \"2021-10-26T11:30:00+03:00\",\n" +
                "    \"PreviousDate\": \"2021-10-23T11:30:00+03:00\",\n" +
                "    \"PreviousURL\": \"\\/\\/www.cbr-xml-daily.ru\\/archive\\/2021\\/10\\/23\\/daily_json.js\",\n" +
                "    \"Timestamp\": \"2021-10-25T19:00:00+03:00\",\n" +
                "    \"Valute\": {\n" +
                "        \"AUD\": {\n" +
                "            \"ID\": \"R01010\",\n" +
                "            \"NumCode\": \"036\",\n" +
                "            \"CharCode\": \"AUD\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Австралийский доллар\",\n" +
                "            \"Value\": 52.6219,\n" +
                "            \"Previous\": 53.1396\n" +
                "        },\n" +
                "        \"AZN\": {\n" +
                "            \"ID\": \"R01020A\",\n" +
                "            \"NumCode\": \"944\",\n" +
                "            \"CharCode\": \"AZN\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Азербайджанский манат\",\n" +
                "            \"Value\": 41.2799,\n" +
                "            \"Previous\": 41.7082\n" +
                "        },\n" +
                "        \"GBP\": {\n" +
                "            \"ID\": \"R01035\",\n" +
                "            \"NumCode\": \"826\",\n" +
                "            \"CharCode\": \"GBP\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Фунт стерлингов Соединенного королевства\",\n" +
                "            \"Value\": 96.6453,\n" +
                "            \"Previous\": 97.6482\n" +
                "        },\n" +
                "        \"AMD\": {\n" +
                "            \"ID\": \"R01060\",\n" +
                "            \"NumCode\": \"051\",\n" +
                "            \"CharCode\": \"AMD\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Армянских драмов\",\n" +
                "            \"Value\": 14.731,\n" +
                "            \"Previous\": 14.8372\n" +
                "        },\n" +
                "        \"BYN\": {\n" +
                "            \"ID\": \"R01090B\",\n" +
                "            \"NumCode\": \"933\",\n" +
                "            \"CharCode\": \"BYN\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Белорусский рубль\",\n" +
                "            \"Value\": 29.0124,\n" +
                "            \"Previous\": 29.1903\n" +
                "        },\n" +
                "        \"BGN\": {\n" +
                "            \"ID\": \"R01100\",\n" +
                "            \"NumCode\": \"975\",\n" +
                "            \"CharCode\": \"BGN\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Болгарский лев\",\n" +
                "            \"Value\": 41.7965,\n" +
                "            \"Previous\": 42.1799\n" +
                "        },\n" +
                "        \"BRL\": {\n" +
                "            \"ID\": \"R01115\",\n" +
                "            \"NumCode\": \"986\",\n" +
                "            \"CharCode\": \"BRL\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Бразильский реал\",\n" +
                "            \"Value\": 12.4119,\n" +
                "            \"Previous\": 12.5216\n" +
                "        },\n" +
                "        \"HUF\": {\n" +
                "            \"ID\": \"R01135\",\n" +
                "            \"NumCode\": \"348\",\n" +
                "            \"CharCode\": \"HUF\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Венгерских форинтов\",\n" +
                "            \"Value\": 22.4412,\n" +
                "            \"Previous\": 22.6966\n" +
                "        },\n" +
                "        \"HKD\": {\n" +
                "            \"ID\": \"R01200\",\n" +
                "            \"NumCode\": \"344\",\n" +
                "            \"CharCode\": \"HKD\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Гонконгских долларов\",\n" +
                "            \"Value\": 90.226,\n" +
                "            \"Previous\": 91.1365\n" +
                "        },\n" +
                "        \"DKK\": {\n" +
                "            \"ID\": \"R01215\",\n" +
                "            \"NumCode\": \"208\",\n" +
                "            \"CharCode\": \"DKK\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Датская крона\",\n" +
                "            \"Value\": 10.9877,\n" +
                "            \"Previous\": 11.0875\n" +
                "        },\n" +
                "        \"USD\": {\n" +
                "            \"ID\": \"R01235\",\n" +
                "            \"NumCode\": \"840\",\n" +
                "            \"CharCode\": \"USD\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Доллар США\",\n" +
                "            \"Value\": 70.1345,\n" +
                "            \"Previous\": 70.8623\n" +
                "        },\n" +
                "        \"EUR\": {\n" +
                "            \"ID\": \"R01239\",\n" +
                "            \"NumCode\": \"978\",\n" +
                "            \"CharCode\": \"EUR\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Евро\",\n" +
                "            \"Value\": 81.7418,\n" +
                "            \"Previous\": 82.4979\n" +
                "        },\n" +
                "        \"INR\": {\n" +
                "            \"ID\": \"R01270\",\n" +
                "            \"NumCode\": \"356\",\n" +
                "            \"CharCode\": \"INR\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Индийских рупий\",\n" +
                "            \"Value\": 93.5095,\n" +
                "            \"Previous\": 94.8333\n" +
                "        },\n" +
                "        \"KZT\": {\n" +
                "            \"ID\": \"R01335\",\n" +
                "            \"NumCode\": \"398\",\n" +
                "            \"CharCode\": \"KZT\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Казахстанских тенге\",\n" +
                "            \"Value\": 16.4945,\n" +
                "            \"Previous\": 16.6363\n" +
                "        },\n" +
                "        \"CAD\": {\n" +
                "            \"ID\": \"R01350\",\n" +
                "            \"NumCode\": \"124\",\n" +
                "            \"CharCode\": \"CAD\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Канадский доллар\",\n" +
                "            \"Value\": 56.8259,\n" +
                "            \"Previous\": 57.4435\n" +
                "        },\n" +
                "        \"KGS\": {\n" +
                "            \"ID\": \"R01370\",\n" +
                "            \"NumCode\": \"417\",\n" +
                "            \"CharCode\": \"KGS\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Киргизских сомов\",\n" +
                "            \"Value\": 82.6988,\n" +
                "            \"Previous\": 83.5372\n" +
                "        },\n" +
                "        \"CNY\": {\n" +
                "            \"ID\": \"R01375\",\n" +
                "            \"NumCode\": \"156\",\n" +
                "            \"CharCode\": \"CNY\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Китайский юань\",\n" +
                "            \"Value\": 10.9908,\n" +
                "            \"Previous\": 11.089\n" +
                "        },\n" +
                "        \"MDL\": {\n" +
                "            \"ID\": \"R01500\",\n" +
                "            \"NumCode\": \"498\",\n" +
                "            \"CharCode\": \"MDL\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Молдавских леев\",\n" +
                "            \"Value\": 40.1227,\n" +
                "            \"Previous\": 40.5855\n" +
                "        },\n" +
                "        \"NOK\": {\n" +
                "            \"ID\": \"R01535\",\n" +
                "            \"NumCode\": \"578\",\n" +
                "            \"CharCode\": \"NOK\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Норвежских крон\",\n" +
                "            \"Value\": 84.2274,\n" +
                "            \"Previous\": 85.0178\n" +
                "        },\n" +
                "        \"PLN\": {\n" +
                "            \"ID\": \"R01565\",\n" +
                "            \"NumCode\": \"985\",\n" +
                "            \"CharCode\": \"PLN\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Польский злотый\",\n" +
                "            \"Value\": 17.7794,\n" +
                "            \"Previous\": 17.9063\n" +
                "        },\n" +
                "        \"RON\": {\n" +
                "            \"ID\": \"R01585F\",\n" +
                "            \"NumCode\": \"946\",\n" +
                "            \"CharCode\": \"RON\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Румынский лей\",\n" +
                "            \"Value\": 16.524,\n" +
                "            \"Previous\": 16.677\n" +
                "        },\n" +
                "        \"XDR\": {\n" +
                "            \"ID\": \"R01589\",\n" +
                "            \"NumCode\": \"960\",\n" +
                "            \"CharCode\": \"XDR\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"СДР (специальные права заимствования)\",\n" +
                "            \"Value\": 99.241,\n" +
                "            \"Previous\": 100.2546\n" +
                "        },\n" +
                "        \"SGD\": {\n" +
                "            \"ID\": \"R01625\",\n" +
                "            \"NumCode\": \"702\",\n" +
                "            \"CharCode\": \"SGD\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Сингапурский доллар\",\n" +
                "            \"Value\": 52.1795,\n" +
                "            \"Previous\": 52.6975\n" +
                "        },\n" +
                "        \"TJS\": {\n" +
                "            \"ID\": \"R01670\",\n" +
                "            \"NumCode\": \"972\",\n" +
                "            \"CharCode\": \"TJS\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Таджикских сомони\",\n" +
                "            \"Value\": 62.2311,\n" +
                "            \"Previous\": 62.863\n" +
                "        },\n" +
                "        \"TRY\": {\n" +
                "            \"ID\": \"R01700J\",\n" +
                "            \"NumCode\": \"949\",\n" +
                "            \"CharCode\": \"TRY\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Турецких лир\",\n" +
                "            \"Value\": 72.1318,\n" +
                "            \"Previous\": 73.575\n" +
                "        },\n" +
                "        \"TMT\": {\n" +
                "            \"ID\": \"R01710A\",\n" +
                "            \"NumCode\": \"934\",\n" +
                "            \"CharCode\": \"TMT\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Новый туркменский манат\",\n" +
                "            \"Value\": 20.0671,\n" +
                "            \"Previous\": 20.2753\n" +
                "        },\n" +
                "        \"UZS\": {\n" +
                "            \"ID\": \"R01717\",\n" +
                "            \"NumCode\": \"860\",\n" +
                "            \"CharCode\": \"UZS\",\n" +
                "            \"Nominal\": 10000,\n" +
                "            \"Name\": \"Узбекских сумов\",\n" +
                "            \"Value\": 65.5278,\n" +
                "            \"Previous\": 66.276\n" +
                "        },\n" +
                "        \"UAH\": {\n" +
                "            \"ID\": \"R01720\",\n" +
                "            \"NumCode\": \"980\",\n" +
                "            \"CharCode\": \"UAH\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Украинских гривен\",\n" +
                "            \"Value\": 26.6894,\n" +
                "            \"Previous\": 26.9387\n" +
                "        },\n" +
                "        \"CZK\": {\n" +
                "            \"ID\": \"R01760\",\n" +
                "            \"NumCode\": \"203\",\n" +
                "            \"CharCode\": \"CZK\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Чешских крон\",\n" +
                "            \"Value\": 31.8301,\n" +
                "            \"Previous\": 32.1517\n" +
                "        },\n" +
                "        \"SEK\": {\n" +
                "            \"ID\": \"R01770\",\n" +
                "            \"NumCode\": \"752\",\n" +
                "            \"CharCode\": \"SEK\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Шведских крон\",\n" +
                "            \"Value\": 81.7133,\n" +
                "            \"Previous\": 82.6604\n" +
                "        },\n" +
                "        \"CHF\": {\n" +
                "            \"ID\": \"R01775\",\n" +
                "            \"NumCode\": \"756\",\n" +
                "            \"CharCode\": \"CHF\",\n" +
                "            \"Nominal\": 1,\n" +
                "            \"Name\": \"Швейцарский франк\",\n" +
                "            \"Value\": 76.5243,\n" +
                "            \"Previous\": 77.2931\n" +
                "        },\n" +
                "        \"ZAR\": {\n" +
                "            \"ID\": \"R01810\",\n" +
                "            \"NumCode\": \"710\",\n" +
                "            \"CharCode\": \"ZAR\",\n" +
                "            \"Nominal\": 10,\n" +
                "            \"Name\": \"Южноафриканских рэндов\",\n" +
                "            \"Value\": 47.5292,\n" +
                "            \"Previous\": 48.4824\n" +
                "        },\n" +
                "        \"KRW\": {\n" +
                "            \"ID\": \"R01815\",\n" +
                "            \"NumCode\": \"410\",\n" +
                "            \"CharCode\": \"KRW\",\n" +
                "            \"Nominal\": 1000,\n" +
                "            \"Name\": \"Вон Республики Корея\",\n" +
                "            \"Value\": 60.0508,\n" +
                "            \"Previous\": 60.314\n" +
                "        },\n" +
                "        \"JPY\": {\n" +
                "            \"ID\": \"R01820\",\n" +
                "            \"NumCode\": \"392\",\n" +
                "            \"CharCode\": \"JPY\",\n" +
                "            \"Nominal\": 100,\n" +
                "            \"Name\": \"Японских иен\",\n" +
                "            \"Value\": 61.7408,\n" +
                "            \"Previous\": 62.1954\n" +
                "        }\n" +
                "    }\n" +
                "}"


        val jsonElement : JsonElement = JsonParser().parse(json_string)

        val res = ValueDeserializer().deserialize(jsonElement, null, null)

        //Log.d(res.toString(), "nechaev");

        assertEquals("  f ", res.toString())

    }

}