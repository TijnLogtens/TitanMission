function [v0,angle] = launching(vs,vp,r)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
vsp=vs-vp;
magvsp=(vsp(1)^2+vsp(2)^2+vsp(3)^2)^0.5;
G=6.67408E-11;
M=5.972E24;%kg
r0=6578140;
v0=(magvsp^2+2*G*M/r0)^0.5;
magr=(r(1)^2+r(2)^2+r(3)^2)^0.5;
magnitude=magr*magvsp;
angle=acosd((r(1)*vsp(1)+r(2)*vsp(2)+r(3)*vsp(3))/magnitude);
end

