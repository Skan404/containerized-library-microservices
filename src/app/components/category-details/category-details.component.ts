import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Import RouterModule
import { BibliotekaService } from '../../services/biblioteka.service';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule], // Dodaj RouterModule
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent implements OnInit {
  category: any = null;
  elements: any[] = [];

  constructor(
    private bibliotekaService: BibliotekaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.bibliotekaService.getCategoryById(id).subscribe((category) => {
        this.category = category;
      });
  
      this.bibliotekaService.getElementsByCategoryId(id).subscribe((elements) => {
        console.log('Pobrane elementy:', elements); // Debugowanie
        this.elements = elements;
      });
    }
  }
  

  deleteElement(elementId: string): void {
    if (confirm('Czy na pewno chcesz usunąć ten element?')) {
      this.bibliotekaService.deleteElement(elementId).subscribe(
        () => {
          this.elements = this.elements.filter(element => element.id !== elementId);
          alert('Element został usunięty.');
        },
        error => {
          console.error('Błąd podczas usuwania elementu:', error);
          alert('Wystąpił błąd podczas usuwania elementu.');
        }
      );
    }
  }
  
}
