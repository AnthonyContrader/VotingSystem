import { Pipe, PipeTransform } from '@angular/core';
import { utentevotanteDTO } from '../dto/utentevotantedto'

@Pipe({
  name: 'filterid'
})
export class FilteridPipe implements PipeTransform {

  transform(itemList: utentevotanteDTO[], searchId: string):utentevotanteDTO[]{
      if(!itemList)
      return[];
   
    if (!searchId)
      return itemList;
   
    
    let filteredList = [];
    let id = Number(searchId);
    if(itemList.length > 0){ 
      itemList.forEach(item => {
        console.log(item.id_utente);
        if(item.id_utente == id){
          filteredList.push(item);
        }
      });

    }
    return filteredList;

  }

}
