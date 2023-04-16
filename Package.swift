// swift-tools-version:5.3
import PackageDescription

let version = "0.1.16"

let xcFrameworkUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip"
let checksumUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip.md5"

do {
    guard let url = Bundle.main.url(forResource: checksumUrl, withExtension:"md5") else {
        return
    }
    let checksumString = try Data(contentsOf: url)
} catch {
    print(error)
}

let packageName = "kbcore"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: xcFrameworkUrl,
            checksum: checksumString
        )
        ,
    ]
)