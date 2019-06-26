function [v1,v2] = ShortWayCalc(rStart,rEnd,f,g,df,dg)
%Calculate the Heliocentric velocity vector.
rsx = rStart(1);
rsy = rStart(2);

rex = rEnd(1);
rey = rEnd(2);

v1x = ((rex - f*rsx)/g)*149.597870E9;
v1y = ((rey - f*rsy)/g)*149.597870E9;

v1 = [v1x v1y];

v2x = df*rsx + dg*v1x;
v2y = df*rsy + dg*v1y;

v2 = [v2x v2y]*149.597870E9;
end

