package com.stardew.view;

import com.stardew.model.TextureID;

public class ShowFarmManager {
    public static TextureID[][] farm1 = new TextureID[100][75];
    static{
        for(int i=10 ; i<16 ; i++){
            for(int j = 10 ; j < 16 ; j++){
                farm1[i][j] = TextureID.cottageTexture;
            }
        }

        for(int i= 3 ; i<9 ; i++){
            for(int j = 3 ; j < 9 ; j++){
                farm1[i][j] = TextureID.greenHouseRegion;
            }
        }

        for(int i=60 ; i<63 ; i++){
            for(int j = 15 ; j<27 ; j++){
                farm1[i][j] = TextureID.lakeTexture;
            }
        }


        for(int i=60 ; i<63 ; i++){
            for(int j = 45 ; j<59 ; j++){
                farm1[i][j] = TextureID.lakeTexture;
            }
        }

        for(int i=50 ; i<55 ; i++){
            for(int j = 65 ; j<72 ; j++){
                farm1[i][j] = TextureID.quarry;
            }
        }
    }


    public static TextureID[][] farm2 = new TextureID[100][75];
    static{
        for(int i=5 ; i<11 ; i++){
            for(int j=10 ; j< 16 ; j++){
                farm2[i][j] = TextureID.cottageTexture;
            }
        }

        for(int i=30 ; i<36 ; i++){
            for(int j=15 ; j<21 ; j++){
                farm2[i][j] = TextureID.greenHouseRegion;
            }
        }

        for(int i=70 ; i<73 ; i++){
            for(int j=50 ; j<65 ; j++){
                farm2[i][j] = TextureID.lakeTexture;
            }
        }

        for(int i=20 ; i<23 ; i++){
            for(int j=50 ; j<68 ; j++){
                farm2[i][j] = TextureID.lakeTexture;
            }
        }

        for(int i=50 ; i<55 ; i++){
            for(int j=30 ; j<37 ; j++){
                farm2[i][j] = TextureID.quarry;
            }
        }
    }
}
