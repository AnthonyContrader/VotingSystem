import { Component, OnInit } from '@angular/core';
import { Voto } from '../../../core/voto/voto.model';
import { VotoService } from '../../../core/voto/voto.service'; 

@Component({
  selector: 'jhi-voti-lista',
  templateUrl: './voti-lista.component.html',
  styleUrls: ['./voti-lista.component.scss']
})
export class VotiListaComponent implements OnInit {

  listaVoti?: Voto[] | null = [];
  totalItems = 0;
  itemsPerPage: number;
  maxItemPage: number;
  page: number;
  searchId: string;
  
  constructor(
    private service: VotoService
    
  ) {
    this.page = 0;
    this.itemsPerPage = 5;
    this.maxItemPage = 0;
    this.searchId = '';
   }

  ngOnInit(): void {
    
    this.getList();
    this.service.getAllUsersVotes().subscribe((max) => {this.maxItemPage = max});
  }

  getList(): void{
    this.service.getAll(this.page, this.itemsPerPage).subscribe((voti) => {this.listaVoti = voti});
  }

  showPlus(): void{
    this.itemsPerPage = this.itemsPerPage + 1;
    if(this.itemsPerPage > this.maxItemPage){
      this.itemsPerPage  = this.maxItemPage;
    }
  
    this.getList();
  }

  showLess(): void{
    this.itemsPerPage = this.itemsPerPage - 1;
    if(this.itemsPerPage < 1){
      this.itemsPerPage  = 1;
    }
    this.getList();
  }

}
