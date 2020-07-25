package soliditycompiler;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import soliditycompiler.SError;
%%
%{
    //una lista para manejar los errores, esto tambien se aplica en el .cup
    //notese que cuando se encuentra un error, crea un SError, le asigna los detalles (entre ellos la especificacion del error)
    //y ademas lo agrega a la lista antes de retornarlo.
    public static LinkedList<SError> ListaErrores = new LinkedList<SError>(); 
%}
%class LexerC
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
simbolo=[\\,/,!,;,#,$,%,=,?,¡,¿,|,_,-]+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%


{espacio} {/*Ignore*/}
int {return new Symbol(sym.Res_Int, yychar, yyline, yytext());}
int2 {return new Symbol(sym.Res_Int2, yychar, yyline, yytext());}
int4 {return new Symbol(sym.Res_Int4, yychar, yyline, yytext());}
int8 {return new Symbol(sym.Res_Int8, yychar, yyline, yytext());}
int16 {return new Symbol(sym.Res_Int16, yychar, yyline, yytext());}
int32 {return new Symbol(sym.Res_Int32, yychar, yyline, yytext());}
int64 {return new Symbol(sym.Res_Int64, yychar, yyline, yytext());}
int128 {return new Symbol(sym.Res_Int128, yychar, yyline, yytext());}
int256 {return new Symbol(sym.Res_Int256, yychar, yyline, yytext());}
if {return new Symbol(sym.Res_If, yychar, yyline, yytext());}
else {return new Symbol(sym.Res_Else, yychar, yyline, yytext());}
address {return new Symbol(sym.Res_Address, yychar, yyline, yytext());}
as {return new Symbol(sym.Res_As, yychar, yyline, yytext());}
bool {return new Symbol(sym.Res_Bool, yychar, yyline, yytext());}
break {return new Symbol(sym.Res_Break, yychar, yyline, yytext());}
bytes {return new Symbol(sym.Res_Bytes, yychar, yyline, yytext());}
bytes2 {return new Symbol(sym.Res_Bytes2, yychar, yyline, yytext());}
bytes4 {return new Symbol(sym.Res_Bytes4, yychar, yyline, yytext());}
bytes8 {return new Symbol(sym.Res_Bytes8, yychar, yyline, yytext());}
bytes16 {return new Symbol(sym.Res_Bytes16, yychar, yyline, yytext());}
bytes32 {return new Symbol(sym.Res_Bytes32, yychar, yyline, yytext());}
bytes64 {return new Symbol(sym.Res_Bytes64, yychar, yyline, yytext());}
bytes128 {return new Symbol(sym.Res_Bytes128, yychar, yyline, yytext());}
bytes256 {return new Symbol(sym.Res_Bytes256, yychar, yyline, yytext());}
byte {return new Symbol(sym.Res_Byte, yychar, yyline, yytext());}
byte2 {return new Symbol(sym.Res_Byte2, yychar, yyline, yytext());}
byte4 {return new Symbol(sym.Res_Byte4, yychar, yyline, yytext());}
byte8 {return new Symbol(sym.Res_Byte8, yychar, yyline, yytext());}
byte16 {return new Symbol(sym.Res_Byte16, yychar, yyline, yytext());}
byte32 {return new Symbol(sym.Res_Byte32, yychar, yyline, yytext());}
byte64 {return new Symbol(sym.Res_Byte64, yychar, yyline, yytext());}
byte128 {return new Symbol(sym.Res_Byte128, yychar, yyline, yytext());}
byte256 {return new Symbol(sym.Res_Byte256, yychar, yyline, yytext());}
constructor {return new Symbol(sym.Res_Constructor, yychar, yyline, yytext());}
continue {return new Symbol(sym.Res_Continue, yychar, yyline, yytext());}
contract {return new Symbol(sym.Res_Contract, yychar, yyline, yytext());}
delete {return new Symbol(sym.Res_Delete, yychar, yyline, yytext());}
do {return new Symbol(sym.Res_Do, yychar, yyline, yytext());}
enum {return new Symbol(sym.Res_Enum, yychar, yyline, yytext());}
false {return new Symbol(sym.Res_False, yychar, yyline, yytext());}
for {return new Symbol(sym.Res_For, yychar, yyline, yytext());}
from {return new Symbol(sym.Res_From, yychar, yyline, yytext());}
function {return new Symbol(sym.Res_Function, yychar, yyline, yytext());}
import {return new Symbol(sym.Res_Import, yychar, yyline, yytext());}
internal {return new Symbol(sym.Res_Internal, yychar, yyline, yytext());}
mapping {return new Symbol(sym.Res_Mapping, yychar, yyline, yytext());}
moddifier {return new Symbol(sym.Res_Moddifier, yychar, yyline, yytext());}
payable {return new Symbol(sym.Res_Payable, yychar, yyline, yytext());}
pragma {return new Symbol(sym.Res_Pragma, yychar, yyline, yytext());}
private {return new Symbol(sym.Res_Private, yychar, yyline, yytext());}
public {return new Symbol(sym.Res_Public, yychar, yyline, yytext());}
return {return new Symbol(sym.Res_Return, yychar, yyline, yytext());}
returns {return new Symbol(sym.Res_Returns, yychar, yyline, yytext());}
solidity {return new Symbol(sym.Res_Solidity, yychar, yyline, yytext());}
string {return new Symbol(sym.Res_String, yychar, yyline, yytext());}
struct {return new Symbol(sym.Res_Struct, yychar, yyline, yytext());}
this {return new Symbol(sym.Res_This, yychar, yyline, yytext());}
true {return new Symbol(sym.Res_True, yychar, yyline, yytext());}
ufixed {return new Symbol(sym.Res_Ufixed, yychar, yyline, yytext());}
uint {return new Symbol(sym.Res_Uint, yychar, yyline, yytext());}
uint2 {return new Symbol(sym.Res_Uint2, yychar, yyline, yytext());}
uint4 {return new Symbol(sym.Res_Uint4, yychar, yyline, yytext());}
uint8 {return new Symbol(sym.Res_Uint8, yychar, yyline, yytext());}
uint16 {return new Symbol(sym.Res_Uint16, yychar, yyline, yytext());}
uint32 {return new Symbol(sym.Res_Uint32, yychar, yyline, yytext());}
uint64 {return new Symbol(sym.Res_Uint64, yychar, yyline, yytext());}
uint128 {return new Symbol(sym.Res_Uint128, yychar, yyline, yytext());}
uint256 {return new Symbol(sym.Res_Uint256, yychar, yyline, yytext());}
var {return new Symbol(sym.Res_Var, yychar, yyline, yytext());}
view {return new Symbol(sym.Res_View, yychar, yyline, yytext());}
while {return new Symbol(sym.Res_While, yychar, yyline, yytext());}
balance {return new Symbol(sym.Trans_Balance, yychar, yyline, yytext());}
call {return new Symbol(sym.Trans_Call, yychar, yyline, yytext());}
callcode {return new Symbol(sym.Trans_Callcode, yychar, yyline, yytext());}
delgatecall {return new Symbol(sym.Trans_Delgatecall, yychar, yyline, yytext());}
send {return new Symbol(sym.Trans_Send, yychar, yyline, yytext());}
transfer {return new Symbol(sym.Trans_Transfer, yychar, yyline, yytext());}
days {return new Symbol(sym.Unit_Days, yychar, yyline, yytext());}
ether {return new Symbol(sym.Unit_Ether, yychar, yyline, yytext());}
finney {return new Symbol(sym.Unit_Finney, yychar, yyline, yytext());}
hours {return new Symbol(sym.Unit_Hours, yychar, yyline, yytext());}
minutes {return new Symbol(sym.Unit_Minutes, yychar, yyline, yytext());}
seconds {return new Symbol(sym.Unit_Seconds, yychar, yyline, yytext());}
szabo {return new Symbol(sym.Unit_Szabo, yychar, yyline, yytext());}
weeks {return new Symbol(sym.Unit_Weeks, yychar, yyline, yytext());}
wei {return new Symbol(sym.Unit_Wei, yychar, yyline, yytext());}
years {return new Symbol(sym.Unit_Years, yychar, yyline, yytext());}
"/**" ("\n*"|.)*( "*/"|"\n*/") {return new Symbol(sym.ComentarioBloque, yychar, yyline, yytext());}
"/**" ("\n"|.)*( "*/"|"\n*/") {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "Comentario de bloque incorrecto"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
("/**") ("\n*"|{D}|{L}|" "|{simbolo})*  {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "Comentario de bloque incorrecto"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
({D}+ | "."{D}+  | {D}* "." {D}+ | {D}+ "." {D}*) "e" ("-"{D}+|{D}+) {return new Symbol(sym.Cientifico, yychar, yyline, yytext());}
({D}+"."{D}*)| ({D}*"."{D}+)  {return new Symbol(sym.Flotante, yychar, yyline, yytext());}
"//".* {/*Ignore*/}
"!" {return new Symbol(sym.NotLogico, yychar, yyline, yytext());}
"&&" {return new Symbol(sym.AndLogico, yychar, yyline, yytext());}
"^" {return new Symbol(sym.Potencia, yychar, yyline, yytext());} //Revisado, funciona
"==" {return new Symbol(sym.Comparacion, yychar, yyline, yytext());}
"!=" {return new Symbol(sym.Diferencia, yychar, yyline, yytext());}
"||" {return new Symbol(sym.OrLogico, yychar, yyline, yytext());} //Revisado, funciona
"<=" {return new Symbol(sym.MenorOIgualQue, yychar, yyline, yytext());}
"<<"  {return new Symbol(sym.CorrerALaIzquierda, yychar, yyline, yytext());} //Revisado, funciona
">>" {return new Symbol(sym.CorrerALaDerecha, yychar, yyline, yytext());} //Revisado, funciona
"<" {return new Symbol(sym.MenorQue, yychar, yyline, yytext());}
">=" {return new Symbol(sym.MayorOIgualQue, yychar, yyline, yytext());}
">" {return new Symbol(sym.MayorQue, yychar, yyline, yytext());}
"&" {return new Symbol(sym.AndBits, yychar, yyline, yytext());}
"|" {return new Symbol(sym.OrBits, yychar, yyline, yytext());}
"~" {return new Symbol(sym.NotBits, yychar, yyline, yytext());} //Revisado, funciona
"%" {return new Symbol(sym.Porcentaje, yychar, yyline, yytext());} //Revisado, funciona
"**"  {return new Symbol(sym.Potencia, yychar, yyline, yytext());}
"+="  {return new Symbol(sym.Sume, yychar, yyline, yytext());} //Revisado, funciona
"-=" {return new Symbol(sym.Reste, yychar, yyline, yytext());} //Revisado, funciona
"*=" {return new Symbol(sym.Multiplique, yychar, yyline, yytext());} //Revisado, funciona
"/=" {return new Symbol(sym.Divida, yychar, yyline, yytext());} //Revisado, funciona
"(" {return new Symbol(sym.AbreParentesis, yychar, yyline, yytext());}
")" {return new Symbol(sym.CierraParentesis, yychar, yyline, yytext());}
"[" {return new Symbol(sym.AbreCorchete, yychar, yyline, yytext());}
"]" {return new Symbol(sym.CierraCorchete, yychar, yyline, yytext());}
"{" {return new Symbol(sym.AbreLlave, yychar, yyline, yytext());}
"}" {return new Symbol(sym.CierraLlave, yychar, yyline, yytext());}
"?" {return new Symbol(sym.Interrogacion, yychar, yyline, yytext());} //Revisado, funciona
":" {return new Symbol(sym.DosPuntos, yychar, yyline, yytext());} //Revisado, funciona
";" {return new Symbol(sym.PuntoYComa, yychar, yyline, yytext());}
"." {return new Symbol(sym.Punto, yychar, yyline, yytext());}
"=" {return new Symbol(sym.Asignacion, yychar, yyline, yytext());}
"+" {return new Symbol(sym.Suma, yychar, yyline, yytext());}
"-" {return new Symbol(sym.Resta, yychar, yyline, yytext());}
"*" {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
"/" {return new Symbol(sym.Division, yychar, yyline, yytext());}
("hex\""|"hex'") ( {D} | "A" | "B" | "C" | "D" | "E" | "F" )+ ("\""|"'")("\\n")* {return new Symbol(sym.Hexadecimal, yychar, yyline, yytext());}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* ("\""|"'") {return new Symbol(sym.Cadena, yychar, yyline, yytext());} //string
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | "\\".| {simbolo} | " ")* ("\""|"'") {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "String no válido"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "String no válido"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
"\\n" | "\\xNN" | "\\uNNNN" | "\\xNN" {return new Symbol(sym.Escape, yychar, yyline, yytext());}
{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
{D}({L}|{D})* {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "Identificador inválido"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
{L}+ {simbolo} ( {simbolo}|{L})+ {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "Identificador inválido"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
 . {SError dato = new SError(yytext(), yyline, yycolumn, "Error Léxico", "token inválido"); ListaErrores.add(dato) ; return new Symbol(sym.Error, yychar, yyline, yytext());}
