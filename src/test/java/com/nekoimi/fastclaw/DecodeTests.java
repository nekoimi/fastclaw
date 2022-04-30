package com.nekoimi.fastclaw;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.security.InvalidParameterException;

/**
 * <p>DecodeTests</p>
 *
 * @author nekoimi 2022/4/30
 */
public class DecodeTests {
    private final String DECODE_SCRIPT = "eval(function(d,f,a,c,b,e){b=function(a){return a.toString(f)};if(!\"\".replace(/^/,String)){for(;a--;)e[b(a)]=c[a]||b(a);c=[function(a){return e[a]}];b=function(){return\"\\\\w+\"};a=1}for(;a--;)c[a]&&(d=d.replace(new RegExp(\"\\\\b\"+b(a)+\"\\\\b\",\"g\"),c[a]));return d}(\"7 9(a,b,c){b=6(b,3)||l;c=c||'\\\\r\\\\n';e(b<1){4 p}4 a.d(u f(\\\".{0,\\\"+b+\\\"}\\\",\\\"g\\\")).j(c)}7 k(a){5 b='';5 c=9(a,8).m('\\\\r\\\\n');o(5 i=0;i<(c.q-1);i++){b=b+s.t((6(c[i],2)-3).h(3))}4 b}\",31,31,\"   10 return var parseInt function  csplit    match if RegExp  toString  join reurl 76 split  for false length  String fromCharCode new\".split(\" \"),0,{}));";

    @Test
    void runDecodeTest() {
        try {
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine engine = engineManager.getEngineByName("nashorn");
            engine.eval(DECODE_SCRIPT);
            String encode = "01000000011011000100000101000011001110100100001100111110001110110110110101000000011100000110110100111100010000110110111000111101010000110011101001000001001110100110110000111110001111000011111101000001001111110110101101101110011100000110111100111011010000110011111001101111010000110011111100111111011011010110110000111011";
            System.out.println(engine.eval("reurl(" + encode + ")"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
