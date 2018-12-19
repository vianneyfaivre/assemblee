import Mandate from 'src/model/Mandate';

export default class LabelService {

    public static getQuality(mandate: Mandate): string {

        let quality = 'Membre';
        
        if(mandate.quality) {

            if(mandate.quality.endsWith("du")) {
                // "membre du" => "membre"
                quality = mandate.quality.split(" du")[0];
            } else {
                quality = mandate.quality;
            }
        }

        return quality;
    }
}