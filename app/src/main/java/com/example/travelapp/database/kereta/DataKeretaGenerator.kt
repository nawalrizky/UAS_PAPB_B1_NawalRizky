package com.example.travelapp.database.kereta

object DataKeretaGenerator {
    fun generateSampleData(): List<dataKereta> {
        return listOf(
//            dataKereta(
//                "Rp130.000", "Malang Kota Lama", "MLK", "08:00", "", "Argo Muria",
//                "Semarang Tawang", "SMT", "11:30", "Ekonomi - B", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp150.000", "Gambir", "GMR", "14:30", "", "Bima",
//                "Surabaya Pasar Turi", "SBI", "20:05", "Eksekutif - A", "5 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp115.000", "Jatinegara", "JNG", "18:45", "", "Lodaya",
//                "Solo Balapan", "SLO", "22:20", "Ekonomi - C", "3 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp170.000", "Wates", "WT", "05:15", "", "Argo Bromo Anggrek",
//                "Surabaya Gubeng", "SGU", "10:50", "Eksekutif - B", "5 jam 35 menit", "2023-12-25"
//            ),
//            dataKereta(
//                "Rp95.000", "Purwosari", "PWS", "12:30", "", "Taksaka",
//                "Yogyakarta", "YK", "18:05", "Ekonomi - C", "5 jam 35 menit", "2023-12-25"
//            ),
//            dataKereta(
//                "Rp120.000", "Surabaya Gubeng", "SGU", "15:45", "", "Bengawan",
//                "Giham", "LPN", "20:20", "Ekonomi - A", "4 jam 35 menit", "2023-12-25"
//            ),
//            dataKereta(
//                "Rp200.000", "Malang", "ML", "09:20", "", "Gajayana",
//                "Yogyakarta", "YK", "14:55", "Eksekutif - B", "5 jam 35 menit", "2023-12-26"
//            ),
//            dataKereta(
//                "Rp110.000", "Pasarsenen", "PSE", "17:00", "", "Argo Parahyangan",
//                "Bandung", "BD", "20:30", "Ekonomi - C", "3 jam 30 menit", "2023-12-26"
//            ),
//            dataKereta(
//                "Rp160.000", "Juanda", "JUA", "11:15", "", "Turangga",
//                "Surabaya Gubeng", "SGU", "16:50", "Eksekutif - A", "5 jam 35 menit", "2023-12-26"
//            ),
//            dataKereta(
//                "Rp180.000", "Yogyakarta", "YK", "08:10", "", "Bima",
//                "Malang", "ML", "13:45", "Eksekutif - B", "5 jam 35 menit", "2023-12-27"
//            ),
//            dataKereta(
//                "Rp115.000", "Lempuyangan", "LPN", "16:30", "", "Argo Muria",
//                "Semarang Tawang", "SMT", "20:00", "Ekonomi - B", "3 jam 30 menit", "2023-12-27"
//            ),
//            dataKereta(
//                "Rp140.000", "Purwosari", "PWS", "10:45", "", "Gajayana",
//                "Malang", "ML", "16:20", "Ekonomi - A", "5 jam 35 menit", "2023-12-27"
//            ),
//            dataKereta(
//                "Rp100.000", "Solo Balapan", "SLO", "13:30", "", "Taksaka",
//                "Yogyakarta", "YK", "19:05", "Ekonomi - C", "5 jam 35 menit", "2023-12-28"
//            ),
//            dataKereta(
//                "Rp175.000", "Wonokromo", "WO", "05:00", "", "Malioboro",
//                "Yogyakarta", "YK", "10:35", "Eksekutif - A", "5 jam 35 menit", "2023-12-28"
//            ),
//            dataKereta(
//                "Rp130.000", "Surabaya Gubeng", "SGU", "14:15", "", "Argo Lawu",
//                "Solo Balapan", "SLO", "19:50", "Ekonomi - B", "5 jam 35 menit", "2023-12-28"
//            ),
//            dataKereta(
//                "Rp195.000", "Surabaya Pasar Turi", "SBI", "08:40", "", "Gajayana",
//                "Yogyakarta", "YK", "14:15", "Eksekutif - B", "5 jam 35 menit", "2023-12-29"
//            ),
//            dataKereta(
//                "Rp105.000", "Lawang", "LW", "16:00", "", "Lodaya",
//                "Solo Balapan", "SLO", "20:35", "Ekonomi - C", "4 jam 35 menit", "2023-12-29"
//            ),
//            dataKereta(
//                "Rp150.000", "Malang", "ML", "12:20", "", "Argo Bromo Anggrek",
//                "Surabaya Gubeng", "SGU", "17:55", "Ekonomi - A", "5 jam 35 menit", "2023-12-29"
//            ),
//            dataKereta(
//                "Rp160.000", "Malang Kota Lama", "MLK", "09:15", "", "Malioboro",
//                "Yogyakarta", "YK", "14:50", "Ekonomi - B", "5 jam 35 menit", "2023-12-30"
//            ),
//            dataKereta(
//                "Rp135.000", "Kepanjen", "KPN", "17:30", "", "Turangga",
//                "Surabaya Pasar Turi", "SBI", "23:05", "Ekonomi - C", "5 jam 35 menit", "2023-12-30"
//            ),
//            dataKereta(
//                "Rp185.000", "Pasar Senen", "PSE", "11:45", "", "Gajayana",
//                "Malang", "ML", "17:20", "Eksekutif - A", "5 jam 35 menit", "2023-12-30"
//            ),
//            dataKereta(
//                "Rp120.000", "Pasar Senen", "PSE", "14:30", "", "Argo Dwipangga",
//                "Surabaya Pasar Turi", "SBI", "19:05", "Eksekutif - A", "4 jam 35 menit", "2023-12-31"
//            ),
//            dataKereta(
//                "Rp95.000", "Bandung", "BD", "09:15", "", "Cirebon Express",
//                "Cirebon", "CBN", "12:50", "Ekonomi - C", "3 jam 35 menit", "2023-12-31"
//            ),
//            dataKereta(
//                "Rp155.000", "Gambir", "GMR", "07:30", "", "Argo Sindoro",
//                "Surabaya Gubeng", "SGU", "13:05", "Eksekutif - B", "5 jam 35 menit", "2023-12-31"
//            ),
//            dataKereta(
//                "Rp110.000", "Yogyakarta", "YK", "15:45", "", "Matarmaja",
//                "Surabaya Pasar Turi", "SBI", "21:20", "Ekonomi - C", "5 jam 35 menit", "2024-01-01"
//            ),
//            dataKereta(
//                "Rp135.000", "Pasar Senen", "PSE", "10:00", "", "Argo Lawu",
//                "Solo Balapan", "SLO", "15:35", "Ekonomi - B", "5 jam 35 menit", "2024-01-01"
//            ),
//            dataKereta(
//                "Rp180.000", "Surabaya Gubeng", "SGU", "13:15", "", "Gajayana",
//                "Malang", "ML", "18:50", "Eksekutif - A", "5 jam 35 menit", "2024-01-02"
//            ),
//            dataKereta(
//                "Rp125.000", "Bandung", "BD", "09:20", "", "Lodaya",
//                "Solo Balapan", "SLO", "14:55", "Ekonomi - C", "5 jam 35 menit", "2024-01-02"
//            ),
//            dataKereta(
//                "Rp145.000", "Malang Kota Lama", "MLK", "12:45", "", "Argo Parahyangan",
//                "Bandung", "BD", "18:20", "Ekonomi - A", "5 jam 35 menit", "2024-01-03"
//            ),
//            dataKereta(
//                "Rp170.000", "Surabaya Pasar Turi", "SBI", "16:30", "", "Argo Muria",
//                "Semarang Tawang", "SMT", "20:05", "Eksekutif - B", "3 jam 30 menit", "2024-01-03"
//            ),
//            dataKereta(
//                "Rp195.000", "Purwosari", "PWS", "14:00", "", "Bima",
//                "Surabaya Gubeng", "SGU", "19:35", "Eksekutif - A", "5 jam 35 menit", "2024-01-04"
//            ),
//            dataKereta(
//                "Rp120.000", "Solo Balapan", "SLO", "11:15", "", "Turangga",
//                "Surabaya Pasar Turi", "SBI", "16:50", "Ekonomi - C", "5 jam 35 menit", "2024-01-04"
//            ),
//            dataKereta(
//                "Rp160.000", "Yogyakarta", "YK", "08:30", "", "Argo Bromo Anggrek",
//                "Surabaya Gubeng", "SGU", "14:05", "Eksekutif - B", "5 jam 35 menit", "2024-01-05"
//            ),
//            dataKereta(
//                "Rp105.000", "Malang", "ML", "18:45", "", "Gajayana",
//                "Yogyakarta", "YK", "00:20", "Ekonomi - C", "5 jam 35 menit", "2024-01-05"
//            ),
//            dataKereta(
//                "Rp140.000", "Wonokromo", "WO", "15:00", "", "Cirebon Express",
//                "Cirebon", "CBN", "18:35", "Ekonomi - A", "3 jam 35 menit", "2024-01-06"
//            ),
//            dataKereta(
//                "Rp175.000", "Surabaya Gubeng", "SGU", "13:15", "", "Argo Sindoro",
//                "Semarang Tawang", "SMT", "18:50", "Eksekutif - B", "5 jam 35 menit", "2024-01-06"
//            ),
//            dataKereta(
//                "Rp130.000", "Gambir", "GMR", "09:30", "", "Lodaya",
//                "Solo Balapan", "SLO", "15:05", "Ekonomi - C", "5 jam 35 menit", "2024-01-07"
//            ),
//            dataKereta(
//                "Rp185.000", "Lawang", "LW", "07:45", "", "Argo Lawu",
//                "Solo Balapan", "SLO", "13:20", "Eksekutif - A", "5 jam 35 menit", "2024-01-07"
//            ),
//            dataKereta(
//                "Rp110.000", "Pasarsenen", "PSE", "16:00", "", "Turangga",
//                "Surabaya Pasar Turi", "SBI", "21:35", "Ekonomi - B", "5 jam 35 menit", "2024-01-08"
//            ),
//            dataKereta(
//                "Rp150.000", "Malang Kota Lama", "MLK", "12:15", "", "Argo Parahyangan",
//                "Bandung", "BD", "17:50", "Ekonomi - A", "5 jam 35 menit", "2024-01-08"
//            ),
//            dataKereta(
//                "Rp160.000", "Surabaya Pasar Turi", "SBI", "09:30", "", "Bima",
//                "Surabaya Gubeng", "SGU", "15:05", "Eksekutif - B", "5 jam 35 menit", "2024-01-09"
//            ),
//            dataKereta(
//                "Rp135.000", "Lempuyangan", "LPN", "14:45", "", "Gajayana",
//                "Malang", "ML", "20:20", "Ekonomi - C", "5 jam 35 menit", "2024-01-09"
//            ),
//            dataKereta(
//                "Rp180.000", "Gambir", "GMR", "18:00", "", "Argo Sindoro",
//                "Surabaya Gubeng", "SGU", "23:35", "Eksekutif - A", "5 jam 35 menit", "2024-01-10"
//            ),
//            dataKereta(
//                "Rp125.000", "Yogyakarta", "YK", "11:15", "", "Matarmaja",
//                "Surabaya Pasar Turi", "SBI", "16:50", "Ekonomi - C", "5 jam 35 menit", "2024-01-10"
//            ),
//            dataKereta(
//                "Rp145.000", "Pasar Senen", "PSE", "08:30", "", "Argo Lawu",
//                "Solo Balapan", "SLO", "14:05", "Ekonomi - B", "5 jam 35 menit", "2024-01-11"
//            ),
//            dataKereta(
//                "Rp170.000", "Surabaya Gubeng", "SGU", "13:45", "", "Gajayana",
//                "Malang", "ML", "19:20", "Eksekutif - A", "5 jam 35 menit", "2024-01-11"
//            ),
//            dataKereta(
//                "Rp115.000", "Bandung", "BD", "10:00", "", "Lodaya",
//                "Solo Balapan", "SLO", "15:35", "Ekonomi - C", "5 jam 35 menit", "2024-01-12"
//            ),
//            dataKereta(
//                "Rp155.000", "Malang Kota Lama", "MLK", "14:15", "", "Argo Parahyangan",
//                "Bandung", "BD", "19:50", "Ekonomi - A", "5 jam 35 menit", "2024-01-12"
//            ),
//            dataKereta(
//                "Rp180.000", "Surabaya Pasar Turi", "SBI", "16:30", "", "Bima",
//                "Surabaya Gubeng", "SGU", "22:05", "Eksekutif - B", "5 jam 35 menit", "2024-01-13"
//            ),
//            dataKereta(
//                "Rp120.000", "Purwosari", "PWS", "12:45", "", "Turangga",
//                "Surabaya Pasar Turi", "SBI", "18:20", "Ekonomi - C", "5 jam 35 menit", "2024-01-13"
//            ),
//            dataKereta(
//                "Rp140.000", "Yogyakarta", "YK", "09:00", "", "Argo Bromo Anggrek",
//                "Surabaya Gubeng", "SGU", "14:35", "Eksekutif - B", "5 jam 35 menit", "2024-01-14"
//            ),
//            dataKereta(
//                "Rp175.000", "Malang", "ML", "18:15", "", "Gajayana",
//                "Yogyakarta", "YK", "23:50", "Ekonomi - C", "5 jam 35 menit", "2024-01-14"
//            ),
//            dataKereta(
//                "Rp195.000", "Wonokromo", "WO", "15:30", "", "Cirebon Express",
//                "Cirebon", "CBN", "19:05", "Ekonomi - A", "3 jam 35 menit", "2024-01-15"
//            ),
//            dataKereta(
//                "Rp130.000", "Surabaya Gubeng", "SGU", "13:45", "", "Argo Sindoro",
//                "Semarang Tawang", "SMT", "19:20", "Eksekutif - B", "5 jam 35 menit", "2024-01-15"
//            ),
//            dataKereta(
//                "Rp110.000", "Gambir", "GMR", "10:00", "", "Lodaya",
//                "Solo Balapan", "SLO", "15:35", "Ekonomi - C", "5 jam 35 menit", "2024-01-16"
//            ),
//            dataKereta(
//                "Rp150.000", "Lawang", "LW", "08:15", "", "Argo Lawu",
//                "Solo Balapan", "SLO", "13:50", "Ekonomi - A", "5 jam 35 menit", "2024-01-16"
//            ),
//            dataKereta(
//                "Rp160.000", "Pasarsenen", "PSE", "17:30", "", "Turangga",
//                "Surabaya Pasar Turi", "SBI", "23:05", "Ekonomi - B", "5 jam 35 menit", "2024-01-17"
//            ),
//            dataKereta(
//                "Rp135.000", "Malang Kota Lama", "MLK", "13:45", "", "Argo Parahyangan",
//                "Bandung", "BD", "19:20", "Ekonomi - A", "5 jam 35 menit", "2024-01-17"
//            ),
//            dataKereta(
//                "Rp180.000", "Surabaya Pasar Turi", "SBI", "10:00", "", "Bima",
//                "Surabaya Gubeng", "SGU", "15:35", "Eksekutif - B", "5 jam 35 menit", "2024-01-18"
//            ),
//            dataKereta(
//                "Rp125.000", "Lempuyangan", "LPN", "15:15", "", "Gajayana",
//                "Malang", "ML", "20:50", "Ekonomi - C", "5 jam 35 menit", "2024-01-18"
//            ),
//            dataKereta(
//                "Rp145.000", "Gambir", "GMR", "18:30", "", "Argo Bromo Anggrek",
//                "Surabaya Gubeng", "SGU", "00:05", "Ekonomi - A", "5 jam 35 menit", "2024-01-19"
//            ),
//            dataKereta(
//                "Rp170.000", "Yogyakarta", "YK", "11:45", "", "Matarmaja",
//                "Surabaya Pasar Turi", "SBI", "17:20", "Ekonomi - C", "5 jam 35 menit", "2024-01-19"
//            ),
//            dataKereta(
//                "Rp130.000", "Yogyakarta", "YK", "08:00", "", "Argo Muria",
//                "Gambir", "GMR", "11:30", "Ekonomi - B", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp150.000", "Yogyakarta", "YK", "14:30", "", "Bima",
//                "Gambir", "GMR", "20:05", "Eksekutif - A", "5 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp115.000", "Yogyakarta", "YK", "18:45", "", "Lodaya",
//                "Gambir", "GMR", "22:20", "Ekonomi - C", "3 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp170.000", "Yogyakarta", "YK", "05:15", "", "Argo Bromo Anggrek",
//                "Gambir", "GMR", "10:50", "Eksekutif - B", "5 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp95.000", "Yogyakarta", "YK", "12:30", "", "Taksaka",
//                "Gambir", "GMR", "18:05", "Ekonomi - C", "5 jam 35 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp130.000", "Malang Kota Lama", "MLK", "08:00", "", "Argo Muria",
//                "Gambir", "GMR", "11:30", "Ekonomi - B", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp150.000", "Malang Kota Lama", "MLK", "09:00", "", "Argo Muria",
//                "Gambir", "GMR", "12:30", "Eksekutif - A", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp120.000", "Malang Kota Lama", "MLK", "10:00", "", "Argo Muria",
//                "Gambir", "GMR", "13:30", "Ekonomi - C", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp170.000", "Malang Kota Lama", "MLK", "11:00", "", "Argo Muria",
//                "Gambir", "GMR", "14:30", "Eksekutif - B", "3 jam 30 menit", "2023-12-24"
//            ),
//            dataKereta(
//                "Rp150.000", "Malang Kota Lama", "MLK", "12:00", "", "Argo Muria",
//                "Gambir", "GMR", "15:00", "Eksekutif - B", "3 jam 30 menit", "2023-12-24"
//            )

        )
    }
}
