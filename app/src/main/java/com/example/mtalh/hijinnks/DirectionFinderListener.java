package com.example.mtalh.hijinnks;

import java.util.List;

/**
 * Created by CP on 12/28/2017.
 */

interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
