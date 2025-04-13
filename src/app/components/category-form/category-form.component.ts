import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BibliotekaService } from '../../services/biblioteka.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss']
})
export class CategoryFormComponent implements OnInit {
  id: string | null = null;
  nazwa: string = '';
  lokalizacja: string = '';

  constructor(
    private bibliotekaService: BibliotekaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Pobierz ID z parametru ścieżki
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      // Załaduj istniejącą kategorię, jeśli ID jest dostępne
      this.bibliotekaService.getCategoryById(this.id).subscribe((category) => {
        this.nazwa = category.nazwa;
        this.lokalizacja = category.lokalizacja;
      });
    }
  }

  saveCategory(): void {
    const categoryData = { nazwa: this.nazwa, lokalizacja: this.lokalizacja };
    if (this.id) {
      // Aktualizacja istniejącej kategorii
      this.bibliotekaService.updateCategory(this.id, categoryData).subscribe(() => {
        alert('Kategoria zaktualizowana!');
        this.router.navigate(['/categories']);
      });
    } else {
      // Dodawanie nowej kategorii
      this.bibliotekaService.addCategory(categoryData).subscribe(() => {
        alert('Biblioteka dodana!');
        this.router.navigate(['/categories']);
      });
    }
  }
}
