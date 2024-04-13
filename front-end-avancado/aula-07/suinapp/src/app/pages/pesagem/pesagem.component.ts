import { Component, NgZone, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { DataBaseService } from '../../services/database.service';
import { ActivatedRoute, Router } from '@angular/router';
import { WEIGHT_COLLECTION } from '../../shared/constants';

@Component({
  selector: 'app-pesagem',
  templateUrl: './pesagem.component.html',
  styleUrls: ['./pesagem.component.css']
})
export class PesagemComponent implements OnInit {
  pesos: any;
  id: string | null = null;
  chartData: any;
  chartOptions: any;
  hasWeight = true;

  constructor(private router: Router, private databaseService: DataBaseService, private route: ActivatedRoute, private ngZone: NgZone) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');

    this.databaseService.getByColumn(this.id!, WEIGHT_COLLECTION, 'pig').subscribe(
      (response) => {
        if (Object.values(response).length === 0) {
          this.hasWeight = false;
          return;
        }

        this.pesos = Object.keys(response).map((key: any) => {
          return {
            key: key,
            ...response[key]
          };
        });

        this.pesos.sort((a: any, b: any) => {
          return new Date(a.date).getTime() - new Date(b.date).getTime();          
        });
        
        this.chartData = {
          labels: this.pesos.map((peso: any) => new Date(peso.date).toLocaleDateString()),
          datasets: [
            {
              label: 'Peso do Suino de Brinco: ' + this.id,
              data: this.pesos.map((peso: any) => peso.weight),
              fill: true,
              borderColor: '#4bc0c0'
            }
          ]
        };
        this.chartOptions = {
          responsive: true,
          maintainAspectRatio: false,
          aspectRatio: 0.6,
          onClick: this.onChartPointClick.bind(this),
          scales: {
            x: {
              title: {
                display: true,
                text: 'Data'
              }
            },
            y: {
              title: {
                display: true,
                text: 'Peso'
              }
            }
          }
        };
      },
      (error) => {
        console.error(error);
      }
    );
  }

  

  onChartPointClick(event: any, items: any) {
    console.log(items);
    if (items.length > 0) {
      const index = items[0].index;

      this.ngZone.run(() => {
        this.router.navigate(['/edicao-peso', this.pesos[index].key]);
      });
    }
  }

}


