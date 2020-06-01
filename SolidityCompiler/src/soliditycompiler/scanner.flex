package soliditycompiler;
import static soliditycompiler.Tokens.*;
%%
%class Lexer
%line
%column
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
simbolo=[\\,/,!,;,#,$,%,=,?,¡,¿,|,_,-]+
%{
    public String lexeme;
    public int line;
    public int column;
%}
%%

int |
int2 |
int4 |
int8 |
int16 |
int32 |
int64 |
int128 |
int256 |
if |
else |
address |
as |
bool |
break |
bytes |
bytes2 |
bytes4 |
bytes8 |
bytes16 |
bytes32 |
bytes64 |
bytes128 |
bytes256 |
byte |
byte2 |
byte4 |
byte8 |
byte16 |
byte32 |
byte64 |
byte128 |
byte256 |
constructor |
continue |
contract |
delete |
do |
else |
enum |
false |
for |
from |
function |
import |
internal |
mapping |
moddifier |
payable |
Pragma |
private |
public |
return |
returns |
solidity |
string |
struct |
this |
true |
ufixed |
uint |
uint2 |
uint4 |
uint8 |
uint16 |
uint32 |
uint64 |
uint128 |
uint256 |
var |
view |
while {line = yyline; column = yycolumn; lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
balance |
call |
callcode |
delgatecall |
send |
transfer {line = yyline; column = yycolumn; lexeme=yytext(); return Transac;}
days |
ether |
finney |
hours |
minutes |
seconds |
szabo |
weeks |
wei |
years {line = yyline; column = yycolumn; lexeme=yytext(); return Units;}
"/**" ("\n*"|.)*( "*/"|"\n*/") {line = yyline; column = yycolumn; return ComentarioBloque;}
"/**" ("\n"|.)*( "*/"|"\n*/") {line = yyline; column = yycolumn; return Error;}
("/**") ("\n*"|{D}|{L}|" "|{simbolo})*  {line = yyline; column = yycolumn; return Error;}
({D}+ | "."{D}+  | {D}* "." {D}+ | {D}+ "." {D}*) "e" ("-"{D}+|{D}+) {line = yyline; column = yycolumn; lexeme = yytext(); return Cientifico;}
({D}+"."{D}*)| ({D}*"."{D}+)  {line = yyline; column = yycolumn; lexeme=yytext(); return Flotante;}
"//".* {/*Ignore*/}
"!" {line = yyline; column = yycolumn; return Not;}
"&&" {line = yyline; column = yycolumn; return And;}
"^" {line = yyline; column = yycolumn; return Potencia;} //Revisado, funciona
"==" {line = yyline; column = yycolumn; return Comparacion;}
"!=" {line = yyline; column = yycolumn; return Diferencia;}
"||" {line = yyline; column = yycolumn;return Or;} //Revisado, funciona
"<=" {line = yyline; column = yycolumn; return MenorOIgualQue;}
"<<"  {line = yyline; column = yycolumn; return CorrerALaIzquierda;} //Revisado, funciona
">>" {line = yyline; column = yycolumn; return CorrerALaDerecha;} //Revisado, funciona
"<" {line = yyline; column = yycolumn; return MenorQue;}
">=" {line = yyline; column = yycolumn; return MayorOIgualQue;}
">" {line = yyline; column = yycolumn; return MayorQue;}
"&" {line = yyline; column = yycolumn; return And;} 
"|" {line = yyline; column = yycolumn; return Or;}
"~" {line = yyline; column = yycolumn; return Not;} //Revisado, funciona
"%" {line = yyline; column = yycolumn; return Porcentaje;} //Revisado, funciona
"**"  {line = yyline; column = yycolumn; return Potencia;}
"+="  {line = yyline; column = yycolumn; return Sume;} //Revisado, funciona
"-=" {line = yyline; column = yycolumn; return Reste;} //Revisado, funciona
"*=" {line = yyline; column = yycolumn; return Multiplique;} //Revisado, funciona
"/=" {line = yyline; column = yycolumn; return Divida;} //Revisado, funciona
"(" {line = yyline; column = yycolumn; return AbreParentesis;}
")" {line = yyline; column = yycolumn; return CierraParentesis;}
"[" {line = yyline; column = yycolumn; return AbreCorchete;}
"]" {line = yyline; column = yycolumn; return CierraCorchete;}
"{" {line = yyline; column = yycolumn; return AbreLlave;}
"}" {line = yyline; column = yycolumn; return CierraLlave;}
"?" {line = yyline; column = yycolumn; return Interrogacion;} //Revisado, funciona
":" {line = yyline; column = yycolumn; return DosPuntos;} //Revisado, funciona
";" {line = yyline; column = yycolumn; return PuntoYComa;}
"." {line = yyline; column = yycolumn; return Punto;}
"=" {line = yyline; column = yycolumn; return Asignacion;}
"+" {line = yyline; column = yycolumn; return Suma;}
"-" {line = yyline; column = yycolumn; return Resta;}
"*" {line = yyline; column = yycolumn; return Multiplicacion;}
"/" {line = yyline; column = yycolumn; return Division;}
("hex\""|"hex'") ( {D} | "A" | "B" | "C" | "D" | "E" | "F" )+ ("\""|"'")("\\n")* {line = yyline; column = yycolumn; lexeme = yytext(); return Hexadecimal;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* ("\""|"'") {line = yyline; column = yycolumn; lexeme = yytext();return String;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | "\\".| {simbolo} | " ")* ("\""|"'") {line = yyline; column = yycolumn; lexeme = yytext(); return ERROR;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* {line = yyline; column = yycolumn;  return ERROR;}
"\\n" | "\\xNN" | "\\uNNNN" | "\\xNN" {line = yyline; column = yycolumn; return Escape;}
{D}+ {line = yyline; column = yycolumn; lexeme=yytext(); return Numero;}
{L}({L}|{D})* {line = yyline; column = yycolumn; lexeme=yytext(); return Identificador;}
{D}({L}|{D})* {line = yyline; column = yycolumn;  return ERROR;}
{L}+ {simbolo} ( {simbolo}|{L})* {line = yyline; column = yycolumn;  return ERROR;}
 . {line = yyline; column = yycolumn; return ERROR;}