<%@ page contentType="text/html; charset=UTF-8"%>
<SCRIPT LANGUAGE="JavaScript">
<!-- Begin
function populate(objForm,selectIndex) {
timeA = new Date(objForm.year.options[objForm.year.selectedIndex].text,
objForm.month.options[objForm.month.selectedIndex].value,1);
timeDifference = timeA - 86400000;
timeB = new Date(timeDifference);
var daysInMonth = timeB.getDate();
for (var i = 0; i < objForm.day.length; i++) {
objForm.day.options[0] = null;
}
for (var i = 0; i < daysInMonth; i++) {
objForm.day.options[i] = new Option(i+1);
}
document.f1.day.options[0].selected = true;
}
function getYears() {

// You can easily customize what years can be used
var years = new Array(1997,1998,1999,2000,2001,2005)

for (var i = 0; i < document.f1.year.length; i++) {
document.f1.year.options[0] = null;
}
timeC = new Date();
currYear = timeC.getFullYear();
for (var i = 0; i < years.length; i++) {
document.f1.year.options[i] = new Option(years[i]);
}
document.f1.year.options[2].selected=true;
}
window.onLoad = getYears;
//  End -->
</script>


<form name=f1>
<table border=0>
<tr>
<td align=center>
<select name='pdate' onChange="populate(this.form,this.form.month.selectedIndex);">
<option value=00 selected>년도 선택</option>
<option value=2018>2018</option>
<option value=2019>2019</option>
<option value=2020>2020</option>
<option value=2021>2021</option>
<option value=2022>2022</option>
<option value=2023>2023</option>
<option value=2024>2024</option>
<option value=2025>2025</option>
<option value=2026>2026</option>
</select>
년
<select name='pdate' onChange="populate(this.form,this.selectedIndex);">
<option value=01 selected>1</option>
<option value=02>2</option>
<option value=03>3</option>
<option value=04>4</option>
<option value=05>5</option>
<option value=06>6</option>
<option value=07>7</option>
<option value=08>8</option>
<option value=09>9</option>
<option value=10>10</option>
<option value=11>11</option>
<option value=12>12</option>
</select>
월
<select name='pdate'>
<option value=01 selected>1</option>
<option value=02>2</option>
<option value=03>3</option>
<option value=04>4</option>
<option value=05>5</option>
<option value=06>6</option>
<option value=07>7</option>
<option value=08>8</option>
<option value=09>9</option>
<option value=10>10</option>
<option value=11>11</option>
<option value=12>12</option>
<option value=13>13</option>
<option value=14>14</option>
<option value=15>15</option>
<option value=16>16</option>
<option value=17>17</option>
<option value=18>18</option>
<option value=19>19</option>
<option value=20>20</option>
<option value=21>21</option>
<option value=22>22</option>
<option value=23>23</option>
<option value=24>24</option>
<option value=25>25</option>
<option value=26>26</option>
<option value=27>27</option>
<option value=28>28</option>
<option value=29>29</option>
<option value=30>30</option>
<option value=31>31</option>
</select>
일
</td>
</tr>

</table>
</form>
