function [v1,v2] = ShortWayCalc(rStart,rEnd,f,g,df,dg)
%Calculate the Heliocentric trajectory injection velocity vector.
rsx = rStart(1);
rsy = rStart(2);

rex = rEnd(1);
rey = rEnd(2);
%rez = rEnd(3);

v1x = (rex - f*rsx)/g;
v1y = (rey - f*rsy)/g;
%v1z = rez/g;

v1 = [v1x v1y]*149.597870E9;

v2x = df*rsx + dg*v1x;
v2y = df*rsy + dg*v1y;
%v2z = dg*v1z;

v2 = [v2x v2y]*149.597870E9;
end
