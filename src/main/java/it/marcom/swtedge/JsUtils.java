/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Marco Monacelli
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package it.marcom.swtedge;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class JsUtils {
    private static Gson gson = new Gson();

    public static Object[] jsonArgsToObjects(String args) {
        Object[] arguments = null;
        JsonElement parmasJosn = gson.fromJson(args, JsonElement.class);
        if (parmasJosn.getAsJsonArray().size() > 0) {
            arguments = new Object[parmasJosn.getAsJsonArray().get(0).getAsJsonArray().size()];
            for (int i = 0; i < parmasJosn.getAsJsonArray().get(0).getAsJsonArray().size(); i++) {
                JsonElement element = parmasJosn.getAsJsonArray().get(0).getAsJsonArray().get(i);
                arguments[i] = jsonToObject(element);
            }
        }
        return arguments;
    }

    public static Object jsonToObject(String json) {
        JsonElement element = gson.fromJson(json, JsonElement.class);
        return jsonToObject(element);
    }

    public static Object jsonToObject(JsonElement element) {
        Object objReturn = null;
        if (element.isJsonPrimitive()) {
            JsonPrimitive primitiveElement = element.getAsJsonPrimitive();
            if (primitiveElement.isNumber()) {
                objReturn = primitiveElement.getAsDouble();
            } else if (primitiveElement.isBoolean()) {
                objReturn = primitiveElement.getAsBoolean();
            } else if (primitiveElement.isString()) {
                String evaluateString = primitiveElement.getAsString();
                Double paserDouble =null;
                try{
                    paserDouble = Double.parseDouble(evaluateString);
                }catch (Exception ex){}
                if(paserDouble!=null){
                    objReturn=paserDouble;
                }else{
                    objReturn=evaluateString;
                }
            } else if (primitiveElement.isJsonNull()) {
                objReturn = null;
            } else if (primitiveElement.isJsonArray()) {
                objReturn = null;
            }
            if (primitiveElement.isJsonObject()) {
                objReturn = null;
            }
        } else {
            if(!element.isJsonNull()){
                objReturn = element.getAsJsonObject();
            }
        }

        return objReturn;
    }

    public static String objectToJson(Object obj) {
        return gson.toJson(obj);
    }


}
