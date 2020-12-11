import { Injectable } from '@angular/core';
import { IVoto } from './voto.model';
import { SERVER_API_URL_2 } from '../../app.constants' 
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { createRequestOption, Pagination } from '../../shared/util/request-util';

@Injectable({
  providedIn: 'root'
})
export class VotoService {

  public resourceUrl = SERVER_API_URL_2 + 'api/votos';
  constructor(
    private http: HttpClient
  ) { }

  
  query(req?: Pagination): Observable<HttpResponse<IVoto[]>> {
    const options = createRequestOption(req);
    return this.http.get<IVoto[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<{}> {
    return this.http.delete(this.resourceUrl + '/' + id);
  }

  read(id: string): Observable<IVoto>{
    return this.http.get<IVoto>(this.resourceUrl + '/' + id);
  }

  create(voto: IVoto): Observable<IVoto> {
    return this.http.post<IVoto>(this.resourceUrl, voto);
  }

  update(voto: IVoto): Observable<IVoto> {
    return this.http.put<IVoto>(this.resourceUrl, voto);
  }
  getAll(page: number, size: number): Observable<IVoto[]> {   
    return this.http.get<IVoto[]>(this.resourceUrl + '?page=' + page + '&size=' + size);
  }

  getAllUsersVotes(): Observable<any>{
    return this.http.get<any>(this.resourceUrl + '/count-utenti-voti');
  }

  getArrNumVoti(idScheda: string): Observable<any[]>{
    return this.http.get<any[]>(this.resourceUrl + '/count-voti/' + idScheda);
  }


}
