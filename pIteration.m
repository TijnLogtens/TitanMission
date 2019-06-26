function [p,atx,i,f,g,df,dg] = pIteration(rStart,rEnd,dTrueAnomaly,TOF,error)
% Function to iteratively calculate the parameter and find the semi-major axis of
% the transfer orbit.

% Define problem independent constant
GM = 1.32684452150E20/((149.597870E9)^3); %AU^3/s^2
r1 = norm(rStart);
r2 = norm(rEnd);

% Method starting values
p = zeros(1, 1001);
i = 1; %Array indices can't be 0 or less
t = zeros(1, 1001);

% Define problem dependent constants
k = r1 * r2 * (1 - cosd(dTrueAnomaly));
l = r1 + r2;
m = r1 * r2 * (1 + cosd(dTrueAnomaly));

% Define limits of parameter
p1 = k/(l+sqrt(2*m));
p2 = k/(l-sqrt(2*m));

% Select trial value parameter
if (deg2rad(dTrueAnomaly) < pi)
    p(1) = p1 + 0.5;
else
    p(1) = p2/2;
end
    atx = (m*k*p(i))/((2*m-l^2)*((p(i))^2)+2*k*l*p(i)-k^2);
    f = 1 - (r2 / p(i))*(1 - cosd(dTrueAnomaly));
    g = (r1 * r2 * sind(dTrueAnomaly))/(sqrt(GM * p(i)));
    df = sqrt(GM / p(i))*tand(dTrueAnomaly / 2)*(((1-cosd(dTrueAnomaly))/p(i)) - (1/r1) - (1/r2));
    dg = 1 - (r1 / p(i))*(1 - cosd(dTrueAnomaly));
    if (atx > 0)
        dE = acos(1 - (r1/atx)*(1 - f));
        t(i) = g + sqrt((atx^3) / GM)*(dE - sin(dE));
    else
        dF = cosd(1 - (r1/atx)*(1 - f));
        t(i) = g + sqrt(((-atx)^3) / GM)*(asind(dF) - dF);
    end
    p(2) = p(1) + 0.1;
    i = i+1;
    while(abs(t(i-1) - TOF) > error && i < 1000)
        % Define trial value for semi-major axis
        atx = (m*k*p(i))/((2*m-l^2)*((p(i))^2)+2*k*l*p(i)-k^2);
        f = 1 - (r2 / p(i))*(1 - cosd(dTrueAnomaly));
        g = (r1 * r2 * sind(dTrueAnomaly))/(sqrt(GM * p(i)));
        df = sqrt(GM / p(i))*tand(dTrueAnomaly / 2)*(((1-cosd(dTrueAnomaly))/p(i)) - (1/r1) - (1/r2));
        dg = 1 - (r1 / p(i))*(1 - cosd(dTrueAnomaly));
        if (atx > 0)
            dE = acos(1 - (r1/atx)*(1 - f));
            t(i) = g + sqrt((atx^3) / GM)*(dE - sin(dE));
        else
            dF = cosd(1 - (r1/atx)*(1 - f));
            t(i) = g + sqrt(((-atx)^3) / GM)*(asind(dF) - dF);
        end
        p(i+1) = p(i) + ((TOF - t(i))*(p(i) - p(i-1)))/(t(i)-t(i-1));
        i = i + 1; % Count iterations
    end
end