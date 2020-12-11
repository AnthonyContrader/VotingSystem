import { Pipe, PipeTransform } from '@angular/core';
import { Voto } from './core/voto/voto.model';

@Pipe({
  name: 'filterid'
})
export class FilteridPipe implements PipeTransform {

  transform(itemList: Voto[], searchId: string):Voto[]{
    if(!itemList)
    return[];
 
  if (!searchId)
    return itemList; 

  const filteredList =  new Array<Voto>();
  
  const id = Number(searchId);
  if(itemList.length > 0){ 
    itemList.forEach(item => {
      if(item.utente === id){
        filteredList.push(item);
      }
    });

  }
  return filteredList;

}

}
