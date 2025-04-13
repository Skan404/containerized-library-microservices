import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root',
})
export class BibliotekaService {
  private apiUrl = '/api/biblioteka';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl);
  }

  deleteCategory(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getCategoryById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
  
  addCategory(category: { nazwa: string; lokalizacja: string }): Observable<any> {
    return this.http.post<any>(this.apiUrl, category);
  }
  
  updateCategory(id: string, category: { nazwa: string; lokalizacja: string }): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, category);
  }
  
  getElementsByCategoryId(categoryId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${categoryId}/elements`);
  }
  
  deleteElement(elementId: string): Observable<void> {
    return this.http.delete<void>(`/api/ksiazki/${elementId}`);
  }
  
  addElement(book: any): Observable<any> {
    return this.http.post<any>('/api/ksiazki', book);
  }
  
  getElementById(id: string): Observable<any> {
    return this.http.get<any>(`/api/ksiazki/${id}`);
  }
  
  updateElement(id: string, element: any): Observable<any> {
    return this.http.put<any>(`/api/ksiazki/${id}`, element);
  }
  
  
  
}
