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


{espacio} {/*Ignore*/}
int {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int;}
int2 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int2;}
int4 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int4;}
int8 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int8;}
int16 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int16;}
int32 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int32;}
int64 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int64;}
int128 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int128;}
int256 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Int256;}
if {line = yyline; column = yycolumn; lexeme=yytext(); return Res_If;}
else {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Else;}
address {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Address;}
as {line = yyline; column = yycolumn; lexeme=yytext(); return Res_As;}
bool {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bool;}
break {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Break;}
bytes {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes;}
bytes2 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes2;}
bytes4 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes4;}
bytes8 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes8;}
bytes16 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes16;}
bytes32 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes32;}
bytes64 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes64;}
bytes128 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes128;}
bytes256 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Bytes256;}
byte {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte;}
byte2 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte2;}
byte4 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte4;}
byte8 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte8;}
byte16 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte16;}
byte32 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte32;}
byte64 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte64;}
byte128 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte128;}
byte256 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Byte256;}
constructor {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Constructor;}
continue {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Continue;}
contract {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Contract;}
delete {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Delete;}
do {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Do;}
enum {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Enum;}
false {line = yyline; column = yycolumn; lexeme=yytext(); return Res_False;}
for {line = yyline; column = yycolumn; lexeme=yytext(); return Res_For;}
from {line = yyline; column = yycolumn; lexeme=yytext(); return Res_From;}
function {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Function;}
import {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Import;}
internal {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Internal;}
mapping {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Mapping;}
moddifier {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Moddifier;}
payable {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Payable;}
Pragma {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Pragma;}
private {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Private;}
public {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Public;}
return {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Return;}
returns {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Returns;}
solidity {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Solidity;}
string {line = yyline; column = yycolumn; lexeme=yytext(); return Res_String;}
struct {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Struct;}
this {line = yyline; column = yycolumn; lexeme=yytext(); return Res_This;}
true {line = yyline; column = yycolumn; lexeme=yytext(); return Res_True;}
ufixed {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Ufixed;}
uint {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint;}
uint2 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint2;}
uint4 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint4;}
uint8 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint8;}
uint16 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint16;}
uint32 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint32;}
uint64 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint64;}
uint128 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint128;}
uint256 {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Uint256;}
var {line = yyline; column = yycolumn; lexeme=yytext(); return Res_Var;}
view {line = yyline; column = yycolumn; lexeme=yytext(); return Res_View;}
while {line = yyline; column = yycolumn; lexeme=yytext(); return Res_While;}
balance {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Balance;}
call {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Call;}
callcode {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Callcode;}
delgatecall {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Delgatecall;}
send {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Send;}
transfer {line = yyline; column = yycolumn; lexeme=yytext(); return Trans_Transfer;}
days {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Days;}
ether {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Ether;}
finney {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Finney;}
hours {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Hours;}
minutes {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Minutes;}
seconds {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Seconds;}
szabo {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Szabo;}
weeks {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Weeks;}
wei {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Wei;}
years {line = yyline; column = yycolumn; lexeme=yytext(); return Unit_Years;}
"/**" ("\n*"|.)*( "*/"|"\n*/") {line = yyline; column = yycolumn; return ComentarioBloque;}
"/**" ("\n"|.)*( "*/"|"\n*/") {line = yyline; column = yycolumn; return Error;}
("/**") ("\n*"|{D}|{L}|" "|{simbolo})*  {line = yyline; column = yycolumn; return Error;}
({D}+ | "."{D}+  | {D}* "." {D}+ | {D}+ "." {D}*) "e" ("-"{D}+|{D}+) {line = yyline; column = yycolumn; lexeme = yytext(); return Cientifico;}
({D}+"."{D}*)| ({D}*"."{D}+)  {line = yyline; column = yycolumn; lexeme=yytext(); return Flotante;}
"//".* {/*Ignore*/}
"!" {line = yyline; column = yycolumn; return NotLogico;}
"&&" {line = yyline; column = yycolumn; return AndLogico;}
"^" {line = yyline; column = yycolumn; return Potencia;} //Revisado, funciona
"==" {line = yyline; column = yycolumn; return Comparacion;}
"!=" {line = yyline; column = yycolumn; return Diferencia;}
"||" {line = yyline; column = yycolumn;return OrLogico;} //Revisado, funciona
"<=" {line = yyline; column = yycolumn; return MenorOIgualQue;}
"<<"  {line = yyline; column = yycolumn; return CorrerALaIzquierda;} //Revisado, funciona
">>" {line = yyline; column = yycolumn; return CorrerALaDerecha;} //Revisado, funciona
"<" {line = yyline; column = yycolumn; return MenorQue;}
">=" {line = yyline; column = yycolumn; return MayorOIgualQue;}
">" {line = yyline; column = yycolumn; return MayorQue;}
"&" {line = yyline; column = yycolumn; return AndBits;} 
"|" {line = yyline; column = yycolumn; return OrBits;}
"~" {line = yyline; column = yycolumn; return NotBits;} //Revisado, funciona
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
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* ("\""|"'") {line = yyline; column = yycolumn; lexeme = yytext();return Cadena;} //string
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | "\\".| {simbolo} | " ")* ("\""|"'") {line = yyline; column = yycolumn; lexeme = yytext(); return Error;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* {line = yyline; column = yycolumn;  return Error;}
"\\n" | "\\xNN" | "\\uNNNN" | "\\xNN" {line = yyline; column = yycolumn; return Escape;}
{D}+ {line = yyline; column = yycolumn; lexeme=yytext(); return Numero;}
{L}({L}|{D})* {line = yyline; column = yycolumn; lexeme=yytext(); return Identificador;}
{D}({L}|{D})* {line = yyline; column = yycolumn;  return Error;}
{L}+ {simbolo} ( {simbolo}|{L})* {line = yyline; column = yycolumn;  return Error;}
 . {line = yyline; column = yycolumn; return Error;}