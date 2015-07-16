AppTitle "BesserWisser":Graphics 640,480,16,2:SetBuffer BackBuffer():SeedRnd MilliSecs()
ft=CreateTimer(50)
Global b0=LoadImage("gfx\b0.jpg"):Global b1=LoadImage("gfx\b1.jpg"):Global b4=LoadImage("gfx\b3.jpg"):Global b2=LoadImage("gfx\b4.jpg"):Global b5=LoadImage("gfx\b5.jpg"):Global b6=LoadImage("gfx\b6.jpg")
Global st=LoadImage("gfx\st.jpg"):Global hs=LoadImage("gfx\hs.jpg"):Global qt=LoadImage("gfx\qt.jpg"):Global t=LoadImage("gfx\t.jpg")
Global m1=LoadImage("gfx\m1.jpg"):Global m2=LoadImage("gfx\m2.jpg"):Global m3=LoadImage("gfx\m3.jpg"):Global m4=LoadImage("gfx\m4.jpg"):Global m5=LoadImage("gfx\m5.jpg"):Global m6=LoadImage("gfx\m6.jpg"):Global m7=LoadImage("gfx\m7.jpg"):Global m8=LoadImage("gfx\m8.jpg")
Global d=10,oper=2,ct=1,init=1
Global wn,diff,rest,ix,tr,gt$,res,give,sm,act,gs$,back,tx#,ty#,lv
fnt=LoadFont("Comic Sans MS",24):SetFont fnt
Dim posx(99):Dim posy(99):Dim pict(8):Dim pnt(4):Dim hsp(4)
a=ReadFile("h"):For f=1 To 4:hsp(f)=ReadInt(a):Next:CloseFile a
Repeat
WaitTimer ft
Select lv
Case 0:Color 20,20,220:DrawBlock b0,0,0:DrawBlock st,50,150:DrawBlock hs,350,150:DrawBlock qt,200,240:If MouseX()>=50 And MouseX()<=300 And MouseY()>=150 And MouseY()<=200 And MouseDown(1)>=1 Then:lv=1:ElseIf MouseX()>=350 And MouseX()<=600 And MouseY()>=150 And MouseY()<=200 And MouseDown(1) >= 1 Then:lv = 5:ElseIf MouseX()>=200 And MouseX()<=450 And MouseY()>=240 And MouseY()<=290 And MouseDown(1) >= 1 Then:End():EndIf 
Case 1:If init Then FlushKeys():tr=MilliSecs():d=10:oper=2:init=0
Cls:ts=(tr+60000-MilliSecs())
DrawBlock b1,0,0
If d>50 Then oper=4
Text 320,50,"Verbleibende Zeit: "+(ts/1000)+" Sekunden",320,50:Text 320,80,"Punkte: "+pnt(1),320,80
x=Rand(1,d)
y=Rand(1,d)
op=Rand(1,oper)
If x<y And op=2 Then x=x+y
If op=3 Then
x=(x/6)+Rand(0,4):y=(x/6)+Rand(0,4)
ElseIf op=4 Then
y=Rand(3,d/10):x=y*Rand(2,10)
EndIf
Select op
Case 1:res = x+y
Text 320,180,"Wieviel ist "+x+"+"+y+"?",320,180
Case 2:res = x-y
Text 320,180,"Wieviel ist "+x+"-"+y+"?",320,180
Case 3:res = x*y
Text 320,180,"Wieviel ist "+x+"*"+y+"?",320,180
Case 4:res = x/y
Text 320,180,"Wieviel ist "+x+"/"+y+"?",320,180
End Select
Locate 310,210
give=Input():ts=(tr+60000-MilliSecs())
If give=res And ts>0 Then
Text 320,260,"Richtig!",320,260: Delay(300):If ts>0 d=d+10 pnt(1)=pnt(1)+10:If hsp(1)<pnt(1) Then hsp(1)=pnt(1)
ElseIf ts<=0 Then
Text 320,260,"Zeit abgelaufen!",320,260:Delay(1000)
Else
Text 320,260,"Leider nicht! (Richtig gewesen wäre: "+res+")",320,260:Delay(700):EndIf  
If ts<=0 Then Delay(1000):hsp(1)=pnt(1):init=1:lv=2
Case 2
If init Then FlushKeys():tr=MilliSecs():init=0:diff=12:rest=0:ix=49
Locate 0,0:Color 10,50,0:ts=(tr+60000-MilliSecs())
DrawBlock b2,0,0
Text 320,90,"Verbleibende Zeit: "+(ts/1000)+" Sekunden",320,90:Text 320,120,"Punkte: "+pnt(2),320,120
SeedRnd MilliSecs()
wn=Rand(0,ix)
wn=(wn*diff)+rest
sm=ReadFile("words.txt")
SeekFile(sm,wn) 
word_mix$=ReadLine(sm)
word$=ReadLine(sm)
CloseFile sm
Select ct:Case 4:diff=14:rest=600:ix=49:Case 8:diff=16:rest=1300:ix=29:Case 11:diff=18:rest=1780:ix=19:Case 14:diff=20:rest=2140:ix=9:Case 17:diff=22:rest=2340:ix=4:End Select 
Text 320,190,"Welches Wort ergeben diese Buchstaben? "+word_mix$,320,190:Locate 300-ct,220:gt$=Input():ts=(tr+60000-MilliSecs())
If Lower(gt$)=word$ And ts>0 Then
Text 320,280,"Richtig!",320,280:ct=ct+1:pnt(2)=pnt(2)+10:Delay(300):If hsp(2)<pnt(2) Then hsp(2)=pnt(2)
ElseIf ts<=0 Then
Text 320,280,"Zeit abgelaufen!",320,280:Delay(1000)
Else 
Text 320,280,"Falsch! (Richtig gewesen wäre: "+word$+")",320,280:Delay(900)
EndIf
If ts<=0 Then ct=1:init=1:lv=3
Case 3
Cls:Color 255,255,150
If init>=1 Then
If init=1 Then FlushKeys():tr=MilliSecs():ct=4:HidePointer()
tx#=Rnd(0,600)
ty#=Rnd(0,400)
init=0
EndIf 
DrawBlock b4,0,0
DrawBlock m3,tx#,ty#
DrawBlock t,MouseY()*1.5,MouseX()
Text 320,10,"Verbleibende Zeit: "+((tr+60000-MilliSecs())/1000)+" Sekunden",320,10:Text 320,40,"Punkte: "+pnt(3),320,40
tx#=tx#+Rnd(-ct,ct)
ty#=ty#+Rnd(-ct,ct)
If tx#>590 Then tx#=590
If tx#<0 Then tx#=0
If ty#>430 Then ty#=430
If ty#<0 Then ty#=0 
If ImagesOverlap(t,MouseY()*1.5,MouseX(),t,tx#,ty#) Then
If hsp(3)<pnt(3) Then hsp(3)=pnt(3)
pnt(3)=pnt(3)+10:ct=ct+1:init=2
EndIf 
If (tr+60000-MilliSecs())<=0 Then
Delay 300:init=1:ShowPointer():lv=4
EndIf 
Case 4
Color 20,40,20
If init Then FlushKeys():tr=MilliSecs():ct=3:init=0
Cls:ts=(tr+60000-MilliSecs())
DrawBlock b5,0,0
For i=1 To ct
pict(i)=Rand(1,8)
Select pict(i)
Case 1:DrawImage m1,i*60,60
Case 2:DrawImage m2,i*60,60
Case 3:DrawImage m3,i*60,60
Case 4:DrawImage m4,i*60,60
Case 5:DrawImage m5,i*60,60
Case 6:DrawImage m6,i*60,60
Case 7:DrawImage m7,i*60,60
Case 8:DrawImage m8,i*60,60
End Select
Next
Print " ":Delay(ct*700):Cls
Locate 0,0
DrawBlock b5,0,0:DrawImage m1,60,200:DrawImage m2,2*60,200:DrawImage m3,3*60,200:DrawImage m4,4*60,200:DrawImage m5,5*60,200:DrawImage m6,6*60,200:DrawImage m7,7*60,200:DrawImage m8,8*60,200
For b = 1 To 8:Text b*60+5,260,"("+b+")":Next
sel=Rand(1,ct)
Text 320,0,"Verbleibende Zeit: "+(ts/1000)+" Sekunden",320,0:Text 320,50,"Punkte: "+pnt(4),320,50
Locate 220,120:gs=Input("Welches war das "+sel+". Bild? "):ts=(tr+60000-MilliSecs())
If gs=pict(sel) And ts>0 Then
Text 320,170,"Richtig!",320,170:pnt(4)=pnt(4)+10:Delay(400):If hsp(4)<pnt(4) Then hsp(4)=pnt(4)
If ct<8 Then ct=ct+1:back=0
ElseIf ts<=0 Then
Text 320,170,"Zeit abgelaufen!",320,170:Delay(1000)
Else
Text 320,170,"Nee, leider nicht...",320,170:Delay(800)
back=back+1
If back = 2 And ct>=4 Then ct=ct-1:back=0
EndIf 
If ts<=0 Then FlushKeys():init=1:lv=5
Case 5
DrawBlock b6,0,0:Color 255,0,0:Text 320,0,"HIGHSCORE",320,0:Text 320,150,"Rechnen: "+pnt(1)+" (Highscore: "+hsp(1)+")",320,150:Text 320,200,"Wörter: "+pnt(2)+" (Highscore: "+hsp(2)+")",320,200:Text 320,250,"Geschick: "+pnt(3)+" (Highscore: "+hsp(3)+")",320,250:Text 320,300,"Gedächtnis: "+pnt(4)+" (Highscore: "+hsp(4)+")",320,300:If KeyHit(28) Then pnt(1)=0:pnt(2)=0:pnt(3)=0:pnt(4)=0:a=WriteFile("h"):For f=1 To 4:WriteInt(a,hsp(f)):Next:CloseFile a:lv=0
End Select:Flip 0:Forever:End