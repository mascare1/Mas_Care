package com.example.data

import com.example.model.MedicineCategory
import com.example.model.MedicineItem
import com.example.model.PharmacyInfo

object PharmacyData {

    val info = PharmacyInfo(
        nameAr = "صيدلية ماس كير",
        nameEn = "MAS CARE PHARMACY",
        sloganAr = "رعاية تليق بك",
        sloganEn = "Care you deserve",
        address = "شارع عمان تقاطع شارع الجزائر - صنعاء",
        phone = "785665658",
        workingHours = "مفتوح 24/7 طوال اليوم مع خدمة التوصيل السريع",
        whatsappNumber = "+967785665658"
    )

    val sampleMedicines = listOf(
        MedicineItem(
            id = "med_1",
            nameAr = "بانادول إكسترا 500/65 ملغ",
            nameEn = "Panadol Extra",
            category = MedicineCategory.MEDICINES,
            dosageForm = "أقراص (24 قرص)",
            priceYer = 1800,
            descriptionAr = "مسكن فعال وقوي للصداع، آلام المفاصل، الصداع النصفي وخافض للحرارة مع مادة الكافيين المعززة للفاعلية.",
            indicationAr = "يؤخذ 1-2 قرص كل 4-6 ساعات حسب الحاجة بعد الأكل.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "GSK GlaxoSmithKline"
        ),
        MedicineItem(
            id = "med_2",
            nameAr = "أوجمانتين 1 جم (أموكسيسيلين وحمض الكلافولانيك)",
            nameEn = "Augmentin 1g",
            category = MedicineCategory.MEDICINES,
            dosageForm = "أقراص مغلفة (14 قرص)",
            priceYer = 6500,
            descriptionAr = "مضاد حيوي واسع المدى لعلاج التهابات الجهاز التنفسي، اللوزتين، الجيوب الأنفية والمسالك البولية.",
            indicationAr = "قرص واحد كل 12 ساعة مع بداية الوجبة حسب إرشادات الطبيب.",
            inStock = true,
            requiresPrescription = true,
            manufacturer = "GSK Pharmaceuticals"
        ),
        MedicineItem(
            id = "med_3",
            nameAr = "بروفين 400 ملغ (إيبوبروفين)",
            nameEn = "Brufen 400mg",
            category = MedicineCategory.MEDICINES,
            dosageForm = "أقراص مغلفة (30 قرص)",
            priceYer = 2200,
            descriptionAr = "مضاد للالتهاب ومسكن لآلام الأسنان، العظام، المفاصل وآلام الدورة الشهرية.",
            indicationAr = "قرص واحد 3 مرات يومياً بعد الوجبات مباشرة مع كوب ماء وفير.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Abbott Laboratories"
        ),
        MedicineItem(
            id = "med_4",
            nameAr = "أوميبرازول 20 ملغ لعلاج الحموضة والقرحة",
            nameEn = "Omeprazole 20mg",
            category = MedicineCategory.MEDICINES,
            dosageForm = "كبسولات مقاومة لعصارة المعدة (28 كبسولة)",
            priceYer = 2800,
            descriptionAr = "مثبط لمضخة البروتون يقلل إفراز أحماض المعدة ويعالج الارتجاع المريئي وقرحة المعدة والاثني عشر.",
            indicationAr = "كبسولة واحدة صباحاً قبل الإفطار بنصف ساعة.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "AstraZeneca"
        ),
        MedicineItem(
            id = "med_5",
            nameAr = "فنتولين بخاخ للاستنشاق 100 ميكروغرام",
            nameEn = "Ventolin Evohaler",
            category = MedicineCategory.MEDICINES,
            dosageForm = "بخاخ فموي (200 جرعة)",
            priceYer = 4200,
            descriptionAr = "موسع سريع للشعب الهوائية لعلاج نوبات الربو الحادة وضيق التنفس والصفير الرئوي.",
            indicationAr = "بخة إلى بختين عند اللزوم، ولا تتجاوز 8 بخات يومياً.",
            inStock = true,
            requiresPrescription = true,
            manufacturer = "GSK"
        ),
        MedicineItem(
            id = "med_6",
            nameAr = "سيتريزين 10 ملغ مضاد للحساسية",
            nameEn = "Cetirizine 10mg",
            category = MedicineCategory.MEDICINES,
            dosageForm = "أقراص (20 قرص)",
            priceYer = 1500,
            descriptionAr = "مضاد للهستامين لعلاج حساسية الأنف، حكة العيون، الرشح وحساسية الجلد (الأرتيكاريا).",
            indicationAr = "قرص واحد يومياً مساءً قبل النوم.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Sandoz"
        ),
        MedicineItem(
            id = "med_7",
            nameAr = "فيتامين سي فوار 1000 ملغ مع الزنك",
            nameEn = "Vitamin C 1000mg + Zinc Effervescent",
            category = MedicineCategory.VITAMINS,
            dosageForm = "أقراص فوارة بنكهة البرتقال (20 قرص)",
            priceYer = 3200,
            descriptionAr = "معزز قوي للمناعة والحماية من نزلات البرد، يدعم إنتاج الكولاجين ومقاومة الأكسدة.",
            indicationAr = "يذاب قرص واحد في نصف كوب ماء يومياً بعد الإفطار.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Bayer"
        ),
        MedicineItem(
            id = "med_8",
            nameAr = "فيتامين د3 50,000 وحدة دولية",
            nameEn = "Vitamin D3 50,000 IU",
            category = MedicineCategory.VITAMINS,
            dosageForm = "كبسولات جيلاتينية رخوة (12 كبسولة)",
            priceYer = 5500,
            descriptionAr = "علاج نقص فيتامين د الشديد، يدعم امتصاص الكالسيوم وصحة العظام والأسنان والمناعة العامة.",
            indicationAr = "كبسولة واحدة أسبوعياً بعد وجبة دسمة لمدة شهرين أو حسب الفحص.",
            inStock = true,
            requiresPrescription = true,
            manufacturer = "Europharm"
        ),
        MedicineItem(
            id = "med_9",
            nameAr = "أوميجا 3 زيت سمك نقي 1000 ملغ",
            nameEn = "Omega-3 Fish Oil 1000mg",
            category = MedicineCategory.VITAMINS,
            dosageForm = "كبسولات نقية (60 كبسولة)",
            priceYer = 7800,
            descriptionAr = "غني بالأحماض الدهنية EPA و DHA لدعم صحة القلب والدماغ والذاكرة وتخفيض الدهون الثلاثية.",
            indicationAr = "كبسولة واحدة أو كبسولتان يومياً مع الطعام.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Nature's Bounty"
        ),
        MedicineItem(
            id = "med_10",
            nameAr = "فيروجلوبين ب12 حديد وزنك وفيتامينات",
            nameEn = "Feroglobin B12 Capsules",
            category = MedicineCategory.VITAMINS,
            dosageForm = "كبسولات ممتدة المفعول (30 كبسولة)",
            priceYer = 4600,
            descriptionAr = "تركيبة لطيفة على المعدة لتعويض نقص الحديد وعلاج فقر الدم ودعم الطاقة والحيوية.",
            indicationAr = "كبسولة واحدة يومياً بعد وجبة الغداء مع الماء.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Vitabiotics"
        ),
        MedicineItem(
            id = "med_11",
            nameAr = "سودوكريم مطهر ومرطب لعلاج طفح الحفاض",
            nameEn = "Sudocrem Antiseptic Cream",
            category = MedicineCategory.MOM_AND_BABY,
            dosageForm = "كريم علاجي ووقائي (125 جم)",
            priceYer = 4900,
            descriptionAr = "الكريم رقم واحد لحماية بشرة الطفل من التهابات وتسلخات الحفاض، وتهدئة حروق الشمس والجروح السطحية.",
            indicationAr = "يوضع طبقة رقيقة على بشرة نظيفة وجافة مع كل غيار حفاض.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Teva"
        ),
        MedicineItem(
            id = "med_12",
            nameAr = "حليب سيميلاك جولد رقم 1 (من الولادة حتى 6 أشهر)",
            nameEn = "Similac Gold 1 with HMO",
            category = MedicineCategory.MOM_AND_BABY,
            dosageForm = "عبوة بودرة حليب رضع (400 جم)",
            priceYer = 9800,
            descriptionAr = "تركيبة حليب أطفال مدعمة بالبريبايوتك HMO ودعم متكامل للجهاز الهضمي والمناعة والتطور الذهني.",
            indicationAr = "تحضر الرضعة حسب جدول التغذية الموضح على العبوة باستخدام ماء مغلي ومبرد.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Abbott Nutrition"
        ),
        MedicineItem(
            id = "med_13",
            nameAr = "بيبانثين كريم مرطب ببروفيتامين B5",
            nameEn = "Bepanthen Moisturizing Cream",
            category = MedicineCategory.SKINCARE,
            dosageForm = "أنبوب كريم مرطب (50 جم)",
            priceYer = 3800,
            descriptionAr = "مرطب طبي خفيف وغير دهني يجدد خلايا الجلد ويلطف البشرة المجهدة والجافة والمتهيجة.",
            indicationAr = "يدهن برفق مرة أو عدة مرات يومياً حسب الحاجة.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Bayer Healthcare"
        ),
        MedicineItem(
            id = "med_14",
            nameAr = "واقي شمس لاروش بوزيه أنثيليوس SPF50+",
            nameEn = "La Roche-Posay Anthelios 50+",
            category = MedicineCategory.SKINCARE,
            dosageForm = "سائل خفيف غير لامع (50 مل)",
            priceYer = 14500,
            descriptionAr = "حماية فائقة من أشعة الشمس فوق البنفسجية UVA/UVB مناسب للبشرة الحساسة والمختلطة مع مظهر غير دهني.",
            indicationAr = "يوضع قبل التعرض للشمس بـ 20 دقيقة ويجدد كل ساعتين.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "La Roche-Posay France"
        ),
        MedicineItem(
            id = "med_15",
            nameAr = "جهاز قياس ضغط الدم الديجيتال أومرون",
            nameEn = "Omron Digital Blood Pressure Monitor",
            category = MedicineCategory.DEVICES,
            dosageForm = "جهاز إلكتروني متكامل للذراع مع شاشة LCD",
            priceYer = 34000,
            descriptionAr = "دقة سريرية عالية لقياس ضغط الدم ومعدل النبض مع كشف اضطراب ضربات القلب وحفظ القراءات السابقة.",
            indicationAr = "استخدمه في وضع الجلوس المسترخي، وضع الكفة على مستوى القلب.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Omron Healthcare Japan"
        ),
        MedicineItem(
            id = "med_16",
            nameAr = "جهاز قياس السكر بالدم أكيو تشيك إنستانت",
            nameEn = "Accu-Chek Instant Blood Glucose Meter",
            category = MedicineCategory.DEVICES,
            dosageForm = "طقم قياس سكر مع قلم وخز و10 شرائط فحص",
            priceYer = 18500,
            descriptionAr = "نتائج فورية خلال 4 ثوانٍ مع مؤشر بصري ملون لمستوى السكر وتصميم عملي ودقيق للمنزل والسفر.",
            indicationAr = "اغسل اليدين جيداً، استخدم شريط فحص نظيف مع قطرة دم دقيقة.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Roche Diabetes Care"
        ),
        MedicineItem(
            id = "med_17",
            nameAr = "حقيبة إسعافات أولية منزلية ومكتبية متكاملة",
            nameEn = "Complete First Aid Emergency Kit",
            category = MedicineCategory.FIRST_AID,
            dosageForm = "حقيبة طبية مجهزة بـ 45 قطعة أساسية",
            priceYer = 8500,
            descriptionAr = "تحتوي على شاش معقم، بلاستر، مطهر جروح، مقص طبي، ملقط، رباط ضاغط، كحول طبي ومسكنات طوارئ.",
            indicationAr = "احتفظ بها في مكان جاف ومعلوم لجميع أفراد الأسرة في المنزل أو السيارة.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Mas Care Quality"
        ),
        MedicineItem(
            id = "med_18",
            nameAr = "محلول مطهر بوفيدون يودين 10% (بيتادين)",
            nameEn = "Povidone Iodine 10% Antiseptic",
            category = MedicineCategory.FIRST_AID,
            dosageForm = "عبوة مطهر طبي (120 مل)",
            priceYer = 1200,
            descriptionAr = "مطهر واسع المجال لتعقيم الجروح والخدوش والحروق السطحية ومنع انتشار العدوى البكتيرية والفطرية.",
            indicationAr = "يطبق بواسطة قطنة أو شاش طبي نظيف على موضع الإصابة.",
            inStock = true,
            requiresPrescription = false,
            manufacturer = "Mundipharma"
        )
    )

    val pharmacyServices = listOf(
        "صرف الوصفات الطبية المعتمدة بجميع أصنافها وتوفير البدائل الأصلية",
        "توصيل سريع وآمن للأدوية إلى المنازل في صنعاء بأسرع وقت",
        "استشارات صيدلانية مجانية وإرشادات تفصيلية للجرعات والتعارضات",
        "قياس الضغط والسكر ومؤشرات الصحة الحيوية مجاناً داخل الصيدلية",
        "توفير أحدث الأجهزة والمستلزمات الطبية وأجهزة الاستنشاق والضغط",
        "قسم متكامل للعناية بالبشرة وحليب ومستلزمات الأطفال والرضع"
    )
}
