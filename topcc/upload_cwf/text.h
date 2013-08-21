/**
@namespace kxutil2::text �ı�����.
@file text.h �ı������Ĳ���.
<pre>
1.����ת�����ж�
2.�Ƚ�
3.��Сдת��
4.����
5.�滻
6.trim
7.�ָ���ƴ��
8.λ�ð�ȫƫ�ƺ;���
9.��ʽ��
10.����
</pre>
*/

#ifndef	_TEXT_H
#define _TEXT_H
#include <boost/regex.hpp>
#include <string>
#include <vector>
#include <set>
#include <utility>
#include <sstream>
using std::string;
using std::vector;
using std::set;
using std::pair;
using std::istringstream;
using std::ostringstream;

namespace kxutil2{ namespace text{
/*-------------------����ת��,�ж�------------------------*/
/// ͨ��ת������:�ַ���ת��Ϊ��������(ֻҪ������֧��operator<<)
template < typename T > 
extern T fromString (const string & s , int nRadix=10)  /// by onebird stringת�� ����֧��cout<<����ת��
{
	istringstream iss (s);
	T t;
	if(16 == nRadix)
		iss >>std::hex>> t;
	else if(8 == nRadix)
		iss >>std::oct>> t;
	else
		iss >> t;

	return t;
}

/// ͨ��ת������:��������(ֻҪ������֧��operator>>)ת��Ϊ�ַ���
template < typename T > 
extern string toString (const T & t ,short nRadix = 10 )  /// ����֧��cout<<���� ת��Ϊstring by onebird
{
	ostringstream oss;
	if(16 == nRadix)
		oss << std::hex<<t;
	else if(8 == nRadix)
		oss << std::oct <<t;
	else
		oss << t;
	return oss.str();
}
///�������ַ�����ת��.
extern string IntToStr (unsigned long iValue);

///���������ַ���ת��.
extern string FloatToStr (float iValue);

///�ַ��������͵�ת��.
extern unsigned long StrToInt (const string & strValue);
///�ַ����򸡵��͵�ת��.
extern unsigned long StrToFloat (const string & strValue);

/// ת����������Ϊ���������֣����ڷǸ����������ܽ�����(�������)
/// ���������������֣�һ �� �� �� �� �� �� �� �� ʮ �� ǧ �� �� �� Ҽ �� �� �� �� ½ �� �� �� ʰ �� Ǫ �f
extern bool ChineseNum2AlabNum(string& strChineseNum, size_t& nAlabNum);  

/// str�Ƿ���ȫ������.
extern bool IsNumberic (string str);


/*-------------------�Ƚ�------------------------*/
/// �Ƚ�str1��str2��ǰnLen�ֽ��Ƿ����,���Դ�Сд,by lvwei.
extern int StrCaseCmp(const char* pStr1, const char* pStr2, size_t nLen);
extern int StrCaseCmp(const string&str1,const string&str2);

/*-------------------��Сдת��------------------*/
/// �򵥵�Сдת��,����ȫ
extern void  ToLower(string &str); 
/// �򵥵Ĵ�дת��,����ȫ
extern void  ToUpper(string &str); 
/// ��ָ����Χ�İ�ȫСдת��
extern bool SafeToLower( string  &  str,  size_t begPos ,  size_t endPos);
/// ��ȫ��Сдת��
extern void SafeToLower( string  &  str); 
/// ��ȫ�Ĵ�дת��
extern void SafeToUpper( string  &  str); 
/// ��ȫ��Сдת��,����һ��Сд�ַ���
extern string SafeGetLower(const string & str); 
/// ��ȫ�Ĵ�дת��,����һ����д�ַ���
extern string SafeGetUpper(const string & str); 

/*----------------------����-------------------------*/
/// ����strFind��strSrc�е�λ�ã����Դ�Сд, by lvwei
extern size_t StrCaseFind(const string&strSrc,const string &strFind);
/// �������strFind��strSrc�е�λ�ã����Դ�Сд, by lvwei
extern size_t StrCaseRFind(const string&strSrc,const string &strFind);

/// �ַ����İ�ȫ���ң����ص�λ�ò�����һ�����ֵ�β�ֽ�
extern size_t SecureStringFind (const string & strStr, const string & strSub,size_t nBeginPos=0);

extern size_t SecureStringRfind (const string & strStr,const string & strSub,size_t nEndPos=string::npos);
  
extern bool TailMatch(const string &  little , const string & big);

/// ��strContent��nPosλ�ÿ�ʼ�������Ҳ��ҵ�nTurn��strSub������λ��,by lvwei
extern size_t StrFind(const string &strContent,const string &strSub,int nTurn=1,size_t nOff=0);
/// ��strContent��nPosλ�ÿ�ʼ����������ҵ�nTurn��strSub������λ��,by lvwei
extern size_t StrRfind(const string &strContent,const string &strSub,int nTurn=1,size_t nOff=string::npos);

/// ��ȡһ���ַ����к��ֵĸ���,by lvwei
extern unsigned long GetChineseCount(const string &strContent);
/// ��ȡһ��Ӣ���ַ��ĸ���,by lvwei
extern unsigned long GetEnCharCount(const string &strContent);  
/// ��ȡһ�����ֵĸ���,by lvwei
extern unsigned long GetNumCharCount(const string &strContent); 
/// ��strContent�л�ȡvecWordList�дʻ�ĸ���,by lvwei
extern int   GetWordCount(const string &strContent,const vector<string>&vecWordList);
/// ��strContent�л�ȡvecWordList�дʻ�ĸ���,strAllMatchΪ����ƥ��Ĵʻ�,���Է��㡡,by lvwei
extern int   GetWordCount(const string &strContent,const vector<string>&vecWordList,string &strAllMatch);
/// ��strContent�л�ȡstrWord����,by lvwei
extern int   GetWordCount(const string &strContent,const string &strWord);

/// �õ�һ���ַ���m~n�������(����m,nΪ�ٷֱ�),by lvwei
extern string  GetSubStr(const string &strContent,float m,float n);
/// ��ȡһ���ַ���strContent�ĵ�����itCurǰ|nCount|���|nCount|���ַ�(ע:nCount����Ϊ����),by lvwei
extern string  GetSubStr(const string &strContent,const string::const_iterator &itCur,int nCount);  

/// ������ʽ��ȫƥ�䣬��ֹƥ�人��ʱƥ���ϵ�һ�����ֵĺ�һ�ֽ�+�ڶ������ֵ�ǰһ�ֽ�,
/// ����"Լ"��ƥ��"�Լ�"��"��"�ĵڶ��ֽ�+"��"�ĵ�һ�ֽ�,��Ϊ�Լ�:D7 D4 BC BA����Լ:D4 BC,by lvwei
extern bool SecurityRegexSearch(const string::const_iterator &itBegin,const string::const_iterator &itEnd,boost::smatch &what,const boost::regex &regexFilter,bool bIsSafe=true);
extern bool SecurityRegexSearch(const string &strContent,const boost::regex &regexFilter,bool bIsSafe=true);
extern bool SecurityRegexSearch(const string &strContent, boost::smatch & what,const boost::regex &regexFilter,bool bIsSafe=true);
/// ��ȫƥ��,����ƥ������,strMatch��ʾƥ�����Ĵ�ƴ��,by lvwei
extern int  SecurityRegexSearchCount(const string &strContent,const boost::regex &regexFilter,bool bIsSafe=true);
extern int  SecurityRegexSearchCount(const string &strContent,string &strAllMatch,const boost::regex &regexFilter,bool bIsSafe=true);

/// ��ƥ����������ʽ����õ�������ʽ��ߵ��ַ��������򷵻�ԭ�����ַ���
extern bool GetAfterRegex(const string &strStr ,string & strGet, boost::regex regexFilter);
/// ��ƥ����������ʽ����õ�������ʽǰ�ߵ��ַ��������򷵻�ԭ�����ַ���
extern bool GetBeforeRegex(const string &strStr ,string & strGet, boost::regex regexFilter);

/// ���ַ����в�������:��nPos��ʼ,numberStartPosΪ����λ��
extern float GetFirstFloatNumber(const string &strContent,size_t nPos=0,size_t *numberStartPos=NULL);
extern int GetFirstIntNumber(const string &strContent,size_t nPos=0,size_t *numberStartPos=NULL);
extern float GetLastFloatNumber(const string &strContent,size_t nPos=string::npos,size_t *numberStartPos=NULL);
extern int GetLastIntNumber(const string &strContent,size_t nPos=string::npos,size_t *numberStartPos=NULL);



/*------------------------�滻------------------*/
/// ��str�е�vecReplaced���еĴʻ��滻��rStr,nType ��Ϊ����Ϊ��ȫ�滻
extern int  ReplaceStrStr(string & str ,const  string &  strReplaced,const string & rStr =" ",int nType=0);	
/// byonebird ��һϵ���ַ�������ָ���ַ���
extern int  ReplaceStrVec(string & str ,const  vector<string>  &vecReplaced ,const string & rStr =" ");	
/// ������(\r|\n|\t| |��)+�滻��rStr(Ĭ��ת����Ӣ�Ŀո�)
extern int  ReplaceBlanksToStr(string & str,const string &rStr=" ");   
/// ��str�е�(\r|\n|\t)�����rStr,�Ľ�by lvwei 2006-08-11 ɾ��\r\n\t by onebird
extern int  ReplaceRNT(string & str ,const string & rStr =" ");	
/// �ϲ�������(\r|\n|\t| |��)�����ֻ�пո��滻Ϊ�ո񣬷����滻ΪrStr, by yangjian 2007-12-7
extern int  ReplaceRNTBToStr(string &str, const string &rStr="\n");

/*-----------------------------trim-----------------*/
/// ���ӵڶ���������0��ʾȥ��Ӣ�ĺ����Ŀո�1��ʾȥ��Ӣ�Ŀո�2��ʾȥ�����Ŀո�
extern void trim(string & str, int flag = 0); 
extern void trim(string & srcStr ,const string & remStr );  /// strim ������ַ��� ǰ��trim by onebird
extern void LTrimAll(string & str , const string & allStr);
extern void RTrimAll(string & str , const string & allStr);
extern void TrimAll(string & str , const string & allStr);

/*-----------------------------�ָ���ƴ��----------------*/
/// segment a line by a seperator
extern void Segment(const string & strInput, char chSeperator,vector < string > &vecSegments);
extern void Segment(const string& strInput, vector<char> vecSeperator, vector<string>& vecSegments);
extern void Segment(const string & strInput,const string& strSeperator,vector<string>&vecSegments);
extern void SegmentIgnoreRepeat(const string& strInput, vector<char> vecSeperator, vector<string>& vecSegments);
extern void SecureSegment(const string& strInput, const string&  strSeperator, vector<string>& vecSegments);

/// ��vecSrc�������ַ���ƴ���������м�ӿո�,����ƴ�������
extern string GetStrFromVec(const vector<string>&vecStr, const string& strJoin="");
extern string GetStrFromSet(const set<string>& setStr, const string& strJoin="");

/*-----------------------------λ�ð�ȫƫ�ƺ;���-----------------*/
/// ��ȡstrContent��ĳһ��������itCurƫ��nOff��λ�ú�Ľ�����,by lvwei
extern string::const_iterator StrIterOff(const string &strContent,const string::const_iterator &itCur,int nOff);
/// Ϊ��ֹ���ֽضϺ��ֵ����,��strContent��nCurPosλ�ÿ�ʼ���Ҽ�nDistance���ַ�
extern size_t SafeRightDistPos(const string &strContent, size_t nCurPos, size_t nDistance);
/// Ϊ��ֹ���ֽضϺ��ֵ����,��strContent��nCurPosλ�ÿ�ʼ�����nDistance���ַ�
extern size_t SafeLeftDistPos(const string &strContent, size_t nCurPos, size_t nDistance);

/// �жϵ�ǰ�������Ƿ�ָ��һ��GBK���ֵ�β�ֽڣ����ǣ������������ȷ��λ�á�
extern bool AdjustItr (string::const_iterator itrStart,string::const_iterator & itrCurrent);

/// �жϵ�ǰλ���Ƿ�ָ��GBK���ֵ�β�ֽڣ����ǣ�������ȷ
extern bool AdjustChinesePos(const string &strText,int nRightPos, int &nCurPos);


/*----------------------------��ʽ��--------------------*/
extern string GetStrWithoutDigitSpace( const string & str);

extern void CleanTxt(const string &strSrc, string &strDst,string &strErased);
extern void CleanTitleStr (string & strTitle); 
extern int _GetByPassToken(string &str,int &pos,int beginPos);	

extern void CleanRepeatedBlank( string & strText);

///  ȥ�����Ŀո��Ӣ�Ŀո� by dongfei
extern void CleanBlank(string& strText);

/// ���strTextƥ��������ʽ�������ı�
extern void CleanRegexTxt(string & strText, boost::regex regex);

/// ���strTextƥ��������ʽ�������ı�,vecText���ر�������ʽ�ֿ��ĸ����ַ�������������������bool����Ϊtrue,����Ϊfalse
extern void CleanRegexTxt(string & strText, boost::regex regex, vector<pair<string,bool> > &vecText);

/// ����itrStart,���ǵ��ָ�����ǰ���ַ� .��������Ӣ�Ŀո�, ��\t : �� ; �����򷵻�ture
extern bool FiltSpacer (string::const_iterator & itrStart,string::const_iterator & itrEnd);			  	

extern int ResetStrZeroChar(string & str , const char ch = ' ' );/// by onebird
/// ��һ���ַ�����ʽ�����ʺ���Ϊsql�����ַ���,���'ת����\'
extern void NormalizeSqlContent(string &strContent);
/// ��һ���ַ�����ʽ�����ʺ���Ϊ�ļ������ַ���
extern void NormalizeFileName(string &strFileName);
///  ����strContent, ��д��Сд��'\t', '\n', '\r', '&nbsp'��ո�ȫ������Ӣ�ı���
extern void NormalizeContent(string &strContent, bool bCutTooLong = true);  
extern int _clearUnicodeHtmlTag(string& strBody);

/*-------------------------------����----------------------------------*/
/// �ж�strCheckContent�Ƿ���strContent��html��ǩ��:��f1234����<a value="f1234">��
extern bool IsInHtmlTag(const string &strContent,const string &strCheckContent);
/// ����������õ���һ������
void ProcessRawPageForCheck(const string &strRawBody, string &strResult);

};};
#endif
