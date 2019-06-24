function [] = launching(vs,vp,r)
% r is the position vector
vsp=vs-vp;
magvsp=(vsp(1)^2+vsp(2)^2+vsp(3)^2)^0.5; %magnitude of vsp
G=6.67408E-11;
M=5.972E24; %mass of earth in kg
r0=6578140; % distance between earth and sun
v0=(magvsp^2+2*G*M/r0)^0.5;
magr=(r(1)^2+r(2)^2+r(3)^2)^0.5; %magnitude of r
magnitude=magr*magvsp;
angle=acosd((r(1)*vsp(1)+r(2)*vsp(2)+r(3)*vsp(3))/magnitude);

%disp(angle)
%disp(v0)
%calculate v0 vector
v=[v0*cos(angle);v0*sin(angle); (v0)^2-(v0*cos(angle))^2-(v0*sin(angle))^2]

%disp(v)

end
